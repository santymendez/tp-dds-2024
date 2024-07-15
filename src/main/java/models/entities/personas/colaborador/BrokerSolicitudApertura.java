package models.entities.personas.colaborador;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Clase que representa al broker que envia las solicitudes de apertura.
 */

public class BrokerSolicitudApertura {

  /**
   * Main.
   */

  public static void main(String[] args) {

    String broker = "tcp://broker.hivemq.com:1883";
    String topic = "dds2024/colaboradores";
    String username = args[0];
    String password = args[1];
    String clientid = args[2];
    String content = "Hello MQTT";
    int qos = 0;

    try {
      MqttConnectOptions options = new MqttConnectOptions();
      options.setUserName(username);
      options.setPassword(password.toCharArray());
      options.setConnectionTimeout(60);
      options.setKeepAliveInterval(60);
      MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
      // connect
      client.connect(options);
      // create message and setup QoS
      MqttMessage message = new MqttMessage(content.getBytes());
      message.setQos(qos);
      // publish message
      client.publish(topic, message);
      System.out.println("Message published");
      System.out.println("topic: " + topic);
      System.out.println("message content: " + content);
      // disconnect
      client.disconnect();
      // close client
      client.close();
    } catch (MqttException e) {
      throw new RuntimeException(e);
    }
  }
}
