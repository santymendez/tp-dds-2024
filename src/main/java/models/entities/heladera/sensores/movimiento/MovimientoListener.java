package models.entities.heladera.sensores.movimiento;

import config.RepositoryLocator;
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
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Clase que representa al listener del sensor de movimiento para el broker.
 */

@Setter
public class MovimientoListener implements IMqttMessageListener {
  private String topic = "sensores/movimiento";
  private GenericRepository repoGenerico;
  private ReportesHeladerasRepository reportesRepository;

  /**
   * Constructor del Listener para los Sensores de Movimento.
   */

  public MovimientoListener() {
    this.repoGenerico = RepositoryLocator.instanceOf(GenericRepository.class);
    this.reportesRepository = RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    int sensorId = Integer.parseInt(message.toString());
    this.activarSensor(sensorId);
  }

  private void activarSensor(long sensorId) {
    Optional<SensorMovimiento> sensor = this.repoGenerico
        .buscarPorId(sensorId, SensorMovimiento.class);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }

    SensorMovimiento sensorMovimiento = sensor.get();
    Heladera heladera = sensorMovimiento.getHeladera();

    if (sensorMovimiento.debeActivarSensor()) {
      MedicionSensor medicion = new MedicionSensor(null, sensorMovimiento.getHeladera());
      sensorMovimiento.recibirMedicion(medicion);
      this.repoGenerico.guardar(medicion);

      Incidente incidente = new Incidente(TipoIncidente.ALERTA, sensorMovimiento.getHeladera());
      incidente.setTipoAlerta(TipoEstado.INACTIVA_FRAUDE);
      sensorMovimiento.desactivarHeladera(incidente);

      ReporteHeladera reporte =
          this.reportesRepository.buscarSemanalPorHeladera(heladera.getId()).get();
      reporte.ocurrioUnaFalla();

      heladera.intentarNotificarSuscriptores();

      this.repoGenerico.guardar(incidente);
    }
  }
}
