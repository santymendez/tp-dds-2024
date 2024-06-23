package models.entities.heladera.sensores.temperatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas máximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura implements IMqttMessageListener {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultTemperatura;
  private String topic = "dds2024/heladeras/almagro/medrano";
  private Heladera heladera;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    String mensaje = mqttMessage.toString();
    this.setUltTemperatura(Float.parseFloat(mensaje));
    this.activarSensor();
  }

  /**
   * Función que desactiva el sensor si la temperatura no está en el rango.
   */

  public void activarSensor() {
    if (ultTemperatura > temperaturaMaxima || ultTemperatura < temperaturaMinima) {
      this.desactivarHeladera();
    }
  }

  public void desactivarHeladera() {
    this.heladera.modificarEstado(TipoEstado.INACTIVA_TEMPERATURA);
    this.heladera.reportarIncidente(TipoEstado.INACTIVA_TEMPERATURA);
  }

  public void activarHeladera() {
    this.heladera.modificarEstado(TipoEstado.ACTIVA);
  }

}
