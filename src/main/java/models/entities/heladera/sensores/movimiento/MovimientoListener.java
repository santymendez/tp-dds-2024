package models.entities.heladera.sensores.movimiento;

import java.util.Optional;
import lombok.Setter;
import models.repositories.heladera.InterfaceSensoresMovimientoRepository;
import models.repositories.heladera.SensoresMovimientoRepository;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Clase que representa al listener del sensor de movimiento para el broker.
 */

@Setter
public class MovimientoListener implements IMqttMessageListener {
  private String topic = "dds2024/sensores-movimiento";
  private InterfaceSensoresMovimientoRepository movimientoRepository;

  public MovimientoListener(InterfaceSensoresMovimientoRepository movimientoRepository) {
    this.movimientoRepository = movimientoRepository;
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) throws Exception {
    int sensorId = Integer.parseInt(message.toString().split("\n")[0]);
    this.activarSensor(sensorId);
  }

  private void activarSensor(int sensorId) {
    Optional<SensorMovimiento> sensor = movimientoRepository.buscar(sensorId);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }
    sensor.get().activarSensor();
  }
}
