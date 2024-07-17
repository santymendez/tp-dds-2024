package models.entities.personas.tarjetas.colaborador;

import com.google.gson.JsonObject;
import dtos.SolicitudAperturaInputDto;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Clase que representa el Publicador de una Solicitud en el Broker.
 */

public class PublicadorSolicitudApertura {
  String broker = "tcp://broker.hivemq.com:1883";
  String topic = "dds2024/colaboradores";

  /**
   * Publica la Solicitud en el Broker.
   *
   * @param nuevaSolicitud Input de la Solicitud recibida por el Controller.
   */

  public void publicarSolicitudEnBroker(SolicitudAperturaInputDto nuevaSolicitud) {
    JsonObject content = new JsonObject();
    content.addProperty("uuid", nuevaSolicitud.getUuid());
    content.addProperty("fechaSolicitud", nuevaSolicitud.getFecha());

    String clientid = "a";
    int qos = 0;

    try {
      MqttConnectOptions options = new MqttConnectOptions();

      options.setConnectionTimeout(60);
      options.setKeepAliveInterval(60);
      MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
      // connect
      client.connect(options);
      // create message and setup QoS
      MqttMessage message = new MqttMessage(content.toString().getBytes());
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
