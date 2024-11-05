package utils.sender.channels;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import config.Config;

/**
 * Esta clase encapsula la funcionalidad de recibir correos electrónicos.
 */

public class EmailConsumer {
  private static final String QUEUE_NAME = Config.getEmailQueue();

  /**
   * Inicializa el consumidor de correos electrónicos.
   */

  public void init() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(Config.getEmailQueueHost());
    try {
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      System.out.println("Esperando mensajes en la cola: " + QUEUE_NAME);

      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        String[] parts = message.split(";", 3);
        String asunto = parts[0];
        String cuerpo = parts[1];
        String destinatario = parts[2];

        EmailSender.getInstance().enviarCorreo(asunto, cuerpo, destinatario);
      };

      channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
