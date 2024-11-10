package brokers.tarjetas;

import com.google.gson.JsonObject;
import config.Config;
import dtos.SolicitudAperturaOutputDto;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Clase que representa el Publicador de una Solicitud en el Broker.
 */

public class PublicadorSolicitudApertura {

  /**
   * Publica la Solicitud en el Broker.
   *
   * @param nuevaSolicitud Input de la Solicitud recibida por el Controller.
   */

  public void publicarSolicitudEnBroker(SolicitudAperturaOutputDto nuevaSolicitud,
                                        String topic, String clientid
  ) {
    JsonObject content = new JsonObject();
    content.addProperty("uuid", nuevaSolicitud.getUuid());
    content.addProperty("heladeraId", nuevaSolicitud.getHeladeraId());

    int qos = 0;

    try {
      MqttConnectOptions options = new MqttConnectOptions();

      options.setConnectionTimeout(60);
      options.setKeepAliveInterval(60);
      MqttClient client = new MqttClient(Config.getBrokerUrl(), clientid, new MemoryPersistence());
      // connect
      client.connect(options);
      // create message and setup QoS
      MqttMessage message = new MqttMessage(content.toString().getBytes());
      message.setQos(qos);
      // publish message
      client.publish(topic, message);
      System.out.println("Mensaje publicado");
      System.out.println("Topic: " + topic);
      System.out.println("Contenido del mensaje: " + content);
      // disconnect
      client.disconnect();
    } catch (MqttException e) {
      throw new RuntimeException(e);
    }
  }
}
