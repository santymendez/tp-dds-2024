package models.entities.heladera.sensores.movimiento;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
public class SensorMovimiento implements IMqttMessageListener {
  private Integer id;
  private Heladera heladera;

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
    this.heladera.getModEstados().modificarEstado(TipoEstado.INACTIVA_FRAUDE);
    this.heladera.getModIncidentes().reportarIncidente(TipoEstado.INACTIVA_FRAUDE, heladera);
  }

  public void activarHeladera() {
    this.heladera.getModEstados().modificarEstado(TipoEstado.ACTIVA);
  }

}