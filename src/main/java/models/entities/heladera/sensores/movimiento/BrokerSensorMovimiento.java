package models.entities.heladera.sensores.movimiento;

import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Clase que representa al broker encargado de enviar las mediciones.
 */

@Setter
public class BrokerSensorMovimiento {

  /**
   * Suscribe al Broker.
   *
   * @param topic Topico al que suscribe.
   */

  public void suscribir(String topic, String clientId) {
    String broker       = "tcp://localhost:1883";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: " + broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      System.out.println("Build our receptor");
      MovimientoListener receptor = new MovimientoListener();

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
