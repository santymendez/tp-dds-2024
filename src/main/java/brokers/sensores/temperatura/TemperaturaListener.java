package brokers.sensores.temperatura;

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
import models.entities.heladera.sensores.SensorTemperatura;
import models.entities.personas.colaborador.suscripcion.TipoSuscripcion;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.searchers.BuscadorTecnicosCercanos;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import services.IncidentesService;

/**
 * Clase que representa al listener del sensor de temperatura para el broker.
 */
@Setter
public class TemperaturaListener implements IMqttMessageListener {
  private GenericRepository repoGenerico;
  private ReportesHeladerasRepository reportesRepository;
  private IncidentesService incidentesService;
  private BuscadorTecnicosCercanos buscadorTecnicosCercanos;

  /**
   * Constructor para el Listener de los Sensores de Temperatura.
   */

  public TemperaturaListener() {
    this.repoGenerico = RepositoryLocator.instanceOf(GenericRepository.class);
    this.reportesRepository = RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);
    this.incidentesService = ServiceLocator.instanceOf(IncidentesService.class);
    this.buscadorTecnicosCercanos = UtilsLocator.instanceOf(BuscadorTecnicosCercanos.class);
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    try {
      Long sensorId = Long.parseLong(message.toString().split(";")[0]);
      Float temp = Float.parseFloat(message.toString().split(";")[1]);
      this.enviarMedicion(sensorId, temp);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }

  /**
   * Metodo que envia la medicion recibida al sensor de temperatura.
   *
   * @param sensorId id que representa al sensor.
   * @param temp medicion a ser enviada.
   */

  public void enviarMedicion(Long sensorId, Float temp) {
    Optional<SensorTemperatura> sensor = this.repoGenerico
        .buscarPorId(sensorId, SensorTemperatura.class);
    if (sensor.isEmpty()) {
      System.out.println("\nNo existe un Sensor de Temperatura con ese ID\n");
      return;
    }

    SensorTemperatura sensorTemperatura = sensor.get();
    Heladera heladera = sensorTemperatura.getHeladera();

    MedicionSensor medicion = new MedicionSensor(temp, heladera);
    sensorTemperatura.recibirMedicion(medicion);
    this.repoGenerico.guardar(medicion);

    if (sensorTemperatura.fueraDeRango(temp)
        && this.incidentesService.noFueAlertadoPor(TipoEstado.INACTIVA_TEMPERATURA, heladera)
    ) {
      Incidente incidente = new Incidente(TipoIncidente.ALERTA, heladera);
      incidente.setTipoAlerta(TipoEstado.INACTIVA_TEMPERATURA);
      this.repoGenerico.guardar(incidente);

      sensorTemperatura.desactivarHeladera();

      this.buscadorTecnicosCercanos.notificarTecnicos(heladera);

      heladera.intentarNotificarSuscriptores(TipoSuscripcion.OCURRIO_DESPERFECTO);

      ReporteHeladera reporte =
          this.reportesRepository.buscarSemanalPorHeladera(heladera.getId()).get();
      reporte.ocurrioUnaFalla();
      this.reportesRepository.modificar(reporte);
    }
  }
}
