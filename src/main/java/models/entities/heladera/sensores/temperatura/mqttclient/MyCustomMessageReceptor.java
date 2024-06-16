package models.entities.heladera.sensores.temperatura.mqttclient;

import lombok.Getter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Clase que representa al receptor del mensaje.
 */

@Getter
public class MyCustomMessageReceptor implements IMqttMessageListener {
  private MqttMessage ultimoMensaje = new MqttMessage();

  @Override
  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    //System.out.println("Message recived from topic " + topic + ": " + mqttMessage.toString());
    ultimoMensaje = mqttMessage;
  }
}