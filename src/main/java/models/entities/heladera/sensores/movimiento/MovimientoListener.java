package models.entities.heladera.sensores.movimiento;

import java.util.Optional;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.MedicionSensor;
import models.entities.reporte.ReporteHeladera;
import models.repositories.RepositoryLocator;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.MedicionesRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.SensoresMovimientoRepository;
import models.repositories.interfaces.InterfaceIncidentesRepository;
import models.repositories.interfaces.InterfaceMedicionesRepository;
import models.repositories.interfaces.InterfaceReportesRepository;
import models.repositories.interfaces.InterfaceSensoresMovimientoRepository;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Clase que representa al listener del sensor de movimiento para el broker.
 */

@Setter
public class MovimientoListener implements IMqttMessageListener {
  private String topic = "sensores/movimiento";
  private InterfaceSensoresMovimientoRepository sensoresMovimientoRepository;
  private InterfaceIncidentesRepository incidentesRepository;
  private InterfaceMedicionesRepository medicionesRepository;
  private InterfaceReportesRepository reportesRepository;

  /**
   * Constructor del Listener para los Sensores de Movimento.
   */

  public MovimientoListener() {
    this.sensoresMovimientoRepository =
        RepositoryLocator
            .get("sensoresMovimientoRepository", SensoresMovimientoRepository.class);
    this.incidentesRepository =
        RepositoryLocator
            .get("incidentesRepository", IncidentesRepository.class);
    this.medicionesRepository =
        RepositoryLocator
            .get("medicionesRepository", MedicionesRepository.class);
    this.reportesRepository =
        RepositoryLocator
            .get("reportesRepository", ReportesRepository.class);
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    int sensorId = Integer.parseInt(message.toString());
    this.activarSensor(sensorId);
  }

  private void activarSensor(long sensorId) {
    Optional<SensorMovimiento> sensor = this.sensoresMovimientoRepository.buscarPorId(sensorId);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }

    SensorMovimiento sensorMovimiento = sensor.get();
    Heladera heladera = sensorMovimiento.getHeladera();

    if (sensorMovimiento.debeActivarSensor()) {
      MedicionSensor medicion = new MedicionSensor(null, sensorMovimiento.getHeladera());
      sensorMovimiento.recibirMedicion(medicion);
      this.medicionesRepository.guardar(medicion);

      Incidente incidente = new Incidente(TipoIncidente.ALERTA, sensorMovimiento.getHeladera());
      incidente.setTipoAlerta(TipoEstado.INACTIVA_FRAUDE);
      sensorMovimiento.desactivarHeladera(incidente);

      ReporteHeladera reporte = this.reportesRepository.buscarSemanalPorHeladera(heladera.getId());
      reporte.ocurrioUnaFalla();

      heladera.intentarNotificarSuscriptores();

      this.incidentesRepository.guardar(incidente);
    }
  }
}
