package models.entities.heladera.sensores.movimiento;

import config.RepositoryLocator;
import config.ServiceLocator;
import config.UtilsLocator;
import java.util.Optional;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.MedicionSensor;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.searchers.BuscadorTecnicosCercanos;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import services.IncidentesService;

/**
 * Clase que representa al listener del sensor de movimiento para el broker.
 */

@Setter
public class MovimientoListener implements IMqttMessageListener {
  private GenericRepository repoGenerico;
  private ReportesHeladerasRepository reportesRepository;
  private IncidentesService incidentesService;
  private BuscadorTecnicosCercanos buscadorTecnicosCercanos;

  /**
   * Constructor del Listener para los Sensores de Movimento.
   */

  public MovimientoListener() {
    this.repoGenerico = RepositoryLocator.instanceOf(GenericRepository.class);
    this.reportesRepository = RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);
    this.incidentesService = ServiceLocator.instanceOf(IncidentesService.class);
    this.buscadorTecnicosCercanos = UtilsLocator.instanceOf(BuscadorTecnicosCercanos.class);
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    try {
      Long sensorId = Long.parseLong(message.toString().split(";")[0]);
      this.activarSensor(sensorId);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void activarSensor(long sensorId) {
    Optional<SensorMovimiento> sensor = this.repoGenerico
        .buscarPorId(sensorId, SensorMovimiento.class);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }

    SensorMovimiento sensorMovimiento = sensor.get();
    Heladera heladera = sensorMovimiento.getHeladera();

    if (sensorMovimiento.debeActivarSensor()
        && this.incidentesService.noFueAlertadoPor(TipoEstado.INACTIVA_FRAUDE, heladera)
    ) {
      MedicionSensor medicion = new MedicionSensor(null, sensorMovimiento.getHeladera());
      sensorMovimiento.recibirMedicion(medicion);
      this.repoGenerico.guardar(medicion);

      Incidente incidente = new Incidente(TipoIncidente.ALERTA, sensorMovimiento.getHeladera());
      incidente.setTipoAlerta(TipoEstado.INACTIVA_FRAUDE);
      this.repoGenerico.guardar(incidente);

      sensorMovimiento.desactivarHeladera();

      this.buscadorTecnicosCercanos.notificarTecnicos(heladera);

      heladera.intentarNotificarSuscriptores();

      ReporteHeladera reporte =
          this.reportesRepository.buscarSemanalPorHeladera(heladera.getId()).get();
      reporte.ocurrioUnaFalla();
      this.reportesRepository.modificar(reporte);
    }
  }
}
