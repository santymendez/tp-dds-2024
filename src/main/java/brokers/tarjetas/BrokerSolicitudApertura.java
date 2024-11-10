package brokers.tarjetas;

import config.Config;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Broker publisher.
 */

@AllArgsConstructor
public class BrokerSolicitudApertura {

  /**
   * Publishes a message to the broker.
   *
   * @param topic  Topic to publish the message.
   * @param clientId Client id.
   */

  public void suscribir(String topic, String clientId) {
    String broker = Config.getBrokerUrl();
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: " + broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      System.out.println("Build our receptor");
      SolicitudAperturaListener solicitudAperturaListener = new SolicitudAperturaListener();

      System.out.println("Now we subscribe to the topic");
      sampleClient.subscribe(topic, solicitudAperturaListener);

      System.out.println("Right! We are subscribed");
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);

      me.printStackTrace();
    }
  }
}
