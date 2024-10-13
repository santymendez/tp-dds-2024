package models.entities.heladera.sensores.temperatura;

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
 * Clase que representa al listener del sensor de temperatura para el broker.
 */
@Setter
public class TemperaturaListener implements IMqttMessageListener {
  private String topic = "sensores/temperatura";
  private GenericRepository repoGenerico;
  private ReportesHeladerasRepository reportesRepository;

  /**
   * Constructor para el Listener de los Sensores de Temperatura.
   */

  public TemperaturaListener() {
    this.repoGenerico =
        RepositoryLocator
            .instanceOf(GenericRepository.class);
    this.reportesRepository =
        RepositoryLocator
            .instanceOf(ReportesHeladerasRepository.class);
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) throws Exception {
    Long sensorId = Long.parseLong(message.toString().split(";")[0]);
    Float temp = Float.parseFloat(message.toString().split(";")[1]);
    this.enviarMedicion(sensorId, temp);
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
      throw new RuntimeException("No existe el sensor");
    }

    SensorTemperatura sensorTemperatura = sensor.get();
    Heladera heladera = sensorTemperatura.getHeladera();

    MedicionSensor medicion = new MedicionSensor(temp, heladera);
    sensorTemperatura.recibirMedicion(medicion);
    this.repoGenerico.guardar(medicion);

    if (!sensorTemperatura.comprobarTemperatura(temp)) {
      Incidente incidente = new Incidente(TipoIncidente.ALERTA, heladera);
      incidente.setTipoAlerta(TipoEstado.INACTIVA_TEMPERATURA);
      this.repoGenerico.guardar(incidente);

      //Se desactiva la heladera, se reporta la falla y se notifican suscriptores
      sensorTemperatura.desactivarHeladera(incidente);
      ReporteHeladera reporte =
          this.reportesRepository.buscarSemanalPorHeladera(heladera.getId()).get();
      reporte.ocurrioUnaFalla();
      heladera.intentarNotificarSuscriptores();
    }
  }
}
