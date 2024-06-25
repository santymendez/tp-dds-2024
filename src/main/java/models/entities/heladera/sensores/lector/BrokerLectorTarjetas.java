package models.entities.heladera.sensores.lector;

import models.entities.heladera.sensores.temperatura.SensorTemperatura;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Clase que representa app encargada de enviar las mediciones.
 */

public class BrokerLectorTarjetas {

  /**
   * Main.
   */

  public static void main(String[] args) {

    String topic        = "lector-tarjetas";
    String content      = "Message from Lector de Tarjetas";
    int qos             = 2;
    String broker       = "tcp://broker.hivemq.com:1883";
    String clientId     = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: " + broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      System.out.println("Build our receptor");
      LectorTarjetas receptor = new LectorTarjetas();

      System.out.println("Now we subscribe to the topic");
      sampleClient.subscribe(topic, receptor);

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
