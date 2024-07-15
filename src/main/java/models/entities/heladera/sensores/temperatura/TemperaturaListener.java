package models.entities.heladera.sensores.temperatura;

import java.util.Optional;
import lombok.Setter;
import models.repositories.heladera.InterfaceSensoresTemperaturaRepository;
import models.repositories.heladera.SensoresTemperaturaRepository;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Clase que representa al listener del sensor de temperatura para el broker.
 */
@Setter
public class TemperaturaListener implements IMqttMessageListener {
  private String topic = "dds2024/heladeras/sensores-temperatura";
  private InterfaceSensoresTemperaturaRepository sensoresTemperaturaRepository;

  public TemperaturaListener(InterfaceSensoresTemperaturaRepository repository) {
    this.sensoresTemperaturaRepository = repository;
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) throws Exception {
    int sensorId = Integer.parseInt(message.toString().split("\n")[0]);
    Float temp = Float.parseFloat(message.toString().split("\n")[1]);
    this.enviarMedicion(sensorId, temp);
  }

  /**
   * Metodo que envia la medicion recibida al sensor de temperatura.
   *
   * @param sensorId id que representa al sensor.
   * @param temp medicion a ser enviada.
   */

  public void enviarMedicion(int sensorId, Float temp) {
    Optional<SensorTemperatura> sensor =
        this.sensoresTemperaturaRepository.buscar(sensorId);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }
    sensor.get().recibirMedicion(temp);
  }
}
