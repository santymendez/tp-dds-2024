package models.entities.heladera.sensores.movimiento;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
//TODO
public class SensorMovimiento implements IMqttMessageListener {
  private Integer id;
  private Heladera heladera;
  private String topic = "sensor-movimiento";

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    this.activarSensor();
  }

  /**
   * Método que recibe la heladera y te activa el sensor si la temperatura no está en el rango.
   */
  
  public void activarSensor() {
    if (!this.heladera.getEstaAbierta()) {
      this.desactivarHeladera();
    }
  }

  public void desactivarHeladera() {
    this.heladera.modificarEstado(TipoEstado.INACTIVA_FRAUDE);
    this.heladera.reportarIncidente(TipoEstado.INACTIVA_FRAUDE);
  }

  public void activarHeladera() {
    this.heladera.modificarEstado(TipoEstado.ACTIVA);
  }

}