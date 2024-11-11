package brokers.tarjetas;

import config.RepositoryLocator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.repositories.imp.TarjetasColaboradoresRepository;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import utils.helpers.ReportesHelper;

/**
 * Listener para la solicitud de apertura.
 */

public class SolicitudAperturaListener implements IMqttMessageListener {

  private final TarjetasColaboradoresRepository tarjetasColaboradoresRepository;
  private final HeladerasRepository heladerasRepository;
  private final ReportesHeladerasRepository reportesHeladerasRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  private final Map<Long, List<Vianda>> viandasPorSolicitud = new HashMap<>();

  /**
   * Constructor del listener.
   */

  public SolicitudAperturaListener() {
    this.tarjetasColaboradoresRepository =
        RepositoryLocator.instanceOf(TarjetasColaboradoresRepository.class);
    this.heladerasRepository =
        RepositoryLocator.instanceOf(HeladerasRepository.class);
    this.reportesHeladerasRepository =
        RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);
    this.colaboradoresRepository =
        RepositoryLocator.instanceOf(ColaboradoresRepository.class);
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    try {
      String uuid = message.toString().split(";")[0];
      Long heladeraId = Long.parseLong(message.toString().split(";")[1]);
      this.intentarAbrirHeladera(uuid, heladeraId, LocalDateTime.now());
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  /**
   * Intenta abrir la heladera.
   *
   * @param uuid              uuid de la tarjeta.
   * @param heladeraId        id de la heladera.
   * @param fechaHoraApertura fecha y hora de la apertura.
   */

  public void intentarAbrirHeladera(String uuid, Long heladeraId, LocalDateTime fechaHoraApertura) {

    TarjetaColaborador tarjeta = this.tarjetasColaboradoresRepository.buscarPorUuid(uuid).get();
    Heladera heladera = this.heladerasRepository.buscarPorId(heladeraId).get();

    if (heladera.intentarAbrirCon(tarjeta, fechaHoraApertura)) {
      UsoTarjetaColaborador ultimoUsoVigente =
          tarjeta.ultimoUsoVigenteEn(heladera, fechaHoraApertura);

      Colaboracion colaboracion = ultimoUsoVigente.getColaboracionAsociada();
      Colaborador colaborador = ultimoUsoVigente.getTarjetaColaborador().getColaborador();

      if (colaboracion.getTipoColaboracion().equals(TipoColaboracion.DONAR_VIANDA)) {
        this.realizarDonacionViandas(colaboracion, heladera, colaborador);
      } else {
        this.realizarDistribucionViandas(colaboracion, colaborador, heladera);
      }

      this.colaboradoresRepository.modificar(colaborador);
    }

  }

  /**
   * Realiza la donacion de viandas.
   *
   * @param colaboracion colaboracion.
   * @param heladera     heladera.
   * @param colaborador  colaborador.
   */

  public void realizarDonacionViandas(
      Colaboracion colaboracion, Heladera heladera, Colaborador colaborador
  ) {
    colaboracion.getDonacionViandas().getViandasDonadas().forEach(vianda -> {
      vianda.setHeladera(heladera);
      heladera.agregarVianda(vianda);
    });

    ReporteHeladera reporteHeladera =
        this.reportesHeladerasRepository.buscarSemanalPorHeladera(heladera.getId()).get();

    ReportesHelper.actualizarReportePorDonacion(reporteHeladera, colaborador,
        colaboracion.getDonacionViandas().getCantViandas()
    );

    colaborador.aumentarReconocimiento(colaboracion);

    this.reportesHeladerasRepository.modificar(reporteHeladera);
  }

  /**
   * Realiza la distribucion de viandas.
   *
   * @param colaboracion colaboracion
   * @param colaborador colaborador
   * @param heladera heladera
   */

  public void realizarDistribucionViandas(
      Colaboracion colaboracion, Colaborador colaborador, Heladera heladera
  ) {
    if (colaboracion.getDistribucionViandas().getHeladeraOrigen().equals(heladera)) {
      List<Vianda> viandasRemovidas = new ArrayList<>(heladera.removerViandas(
          colaboracion.getDistribucionViandas().getCantViandasDistribuidas()
      ));

      this.viandasPorSolicitud.put(colaboracion.getId(), viandasRemovidas);

    } else {
      List<Vianda> viandasPorColocar =
          new ArrayList<>(viandasPorSolicitud.get(colaboracion.getId()));

      viandasPorColocar.forEach(vianda -> {
        vianda.setHeladera(heladera);
        heladera.agregarVianda(vianda);
      });

      colaborador.aumentarReconocimiento(colaboracion);

      this.viandasPorSolicitud.remove(colaboracion.getId());

      ReporteHeladera reporteOrigen =
          this.reportesHeladerasRepository.buscarSemanalPorHeladera(heladera.getId()).get();
      ReporteHeladera reporteDestino =
          this.reportesHeladerasRepository.buscarSemanalPorHeladera(heladera.getId()).get();

      ReportesHelper.actualizarReportePorDistribucion(
          reporteOrigen, reporteDestino, colaborador,
          colaboracion.getDistribucionViandas().getCantViandasDistribuidas()
      );

      this.reportesHeladerasRepository.modificar(reporteOrigen);
      this.reportesHeladerasRepository.modificar(reporteDestino);
    }
  }

}
