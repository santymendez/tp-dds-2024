package models.entities.heladera.sensores.lector;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Clase que representa al lector de tarjetas de colaboradores.
 */

@Getter
@Setter
//TODO
public class LectorTarjetas implements IMqttMessageListener {
  private Heladera heladera;
  private String topic = "lector-tarjetas";

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    String codigo = mqttMessage.toString();
    this.leerCodigo(codigo);
  }

  private void leerCodigo(String codigo) {
    this.heladera.intentarAbrir(codigo);
  }
}
