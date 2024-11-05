package utils.sender.channels;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import config.Config;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Esta clase encapsula la funcionalidad de envío de correos electrónicos.
 * Se realiza a través de un servidor SMTP configurado con Gmail.
 * Se recomienda utilizar el metodo enviar() para enviar un mensaje de correo
 * electrónico especificando el destinatario y el mensaje a enviar.
 */

@Slf4j
public class EmailSender implements SenderInterface {
  private final String nombreDeUsuario = Config.getEmailUser();
  private final String contrasenia = Config.getEmailApiKey();
  private final Session sesion;

  private static final Logger logger = LogManager.getLogger(EmailSender.class);
  private static EmailSender instance;
  private static final String QUEUE_NAME = Config.getEmailQueue();

  private EmailSender() {
    if (nombreDeUsuario == null || contrasenia == null) {
      throw new IllegalStateException("El nombre de usuario o la contraseña no pueden ser nulos");
    }

    Properties propiedadesDelServidorDeCorreo = new Properties();
    propiedadesDelServidorDeCorreo.put("mail.smtp.host", "smtp.gmail.com");
    propiedadesDelServidorDeCorreo.put("mail.smtp.auth", "true");
    propiedadesDelServidorDeCorreo.put("mail.smtp.port", "587");
    propiedadesDelServidorDeCorreo.put("mail.smtp.starttls.enable", "true");

    sesion = Session.getInstance(propiedadesDelServidorDeCorreo, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(nombreDeUsuario, contrasenia);
      }
    });
  }

  /**
   * Metodo para instanciar el EmailSender.
   *
   * @return la instancia del EmailSender.
   */
  public static EmailSender getInstance() {
    if (instance == null) {
      instance = new EmailSender();
    }
    return instance;
  }

  /**
   * Enviar el mensaje a la cola de RabbitMQ.
   *
   * @param mensajeAenviar el mensaje que se quiere enviar
   * @param destinatario   el destinatario al cual se quiere enviar el mensaje
   */

  public void enviar(Mensaje mensajeAenviar, String destinatario) {
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost(Config.getEmailQueueHost());
      try (Connection connection = factory.newConnection();
           Channel channel = connection.createChannel()) {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message =
            mensajeAenviar.getAsunto() + ";" + mensajeAenviar.getCuerpo() + ";" + destinatario;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        logger.info("Mensaje enviado a la cola: {}", message);
        log.info("Mensaje enviado a la cola: {}", message);
      }
    } catch (Exception e) {
      logger.error("Error al enviar el mensaje a la cola: ", e);
      throw new RuntimeException("Error al enviar el mensaje a la cola: " + e.getMessage());
    }
  }

  /**
   * Metodo para enviar un correo electrónico.
   *
   * @param asunto      El asunto del correo.
   * @param cuerpo      El cuerpo del correo.
   * @param destinatario El destinatario del correo.
   */
  public void enviarCorreo(String asunto, String cuerpo, String destinatario) {
    try {
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(nombreDeUsuario));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
      message.setSubject(asunto);
      message.setText(cuerpo);

      Transport.send(message);
      logger.info("Correo enviado a: {}", destinatario);
      log.info("Correo enviado a: {}", destinatario);
    } catch (MessagingException e) {
      logger.error("Error al enviar el correo electrónico: ", e);
      throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage());
    }
  }
}
