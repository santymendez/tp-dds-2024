package utils.sender.channels;

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
import java.util.concurrent.CompletableFuture;
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

public class EmailSender implements SenderInterface {
  private final String nombreDeUsuario = Config.getEmailUser();
  private final String contrasenia = Config.getEmailApiKey(); // Acá se usa la API key
  private final Session sesion;

  private static final Logger logger = LogManager.getLogger(EmailSender.class);
  private static EmailSender instance;

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
   * Se encarga de enviar el mensaje al destinatario de manera asincrónica.
   * Caso de que ocurra un error se lanza una excepción indicando
   * por qué no se pudo realizar el envío.
   *
   * @param mensajeAenviar el mensaje que se quiere enviar
   * @param destinatario   el destinatario al cual se quiere enviar el mensaje
   */

  public void enviarAsync(Mensaje mensajeAenviar, String destinatario) {
    CompletableFuture.runAsync(() -> {
      try {
        Message message = new MimeMessage(sesion);
        message.setFrom(new InternetAddress(nombreDeUsuario)); // Dirección de quien lo envía
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject(mensajeAenviar.getAsunto());
        message.setText(mensajeAenviar.getCuerpo());

        Transport.send(message);

        logger.info("Correo enviado exitosamente a {}", destinatario);
        System.out.println("Correo enviado exitosamente a " + destinatario);
      } catch (MessagingException e) {
        logger.error("Error al enviar el correo electrónico: ", e);
        System.out.println("Error al enviar el correo electrónico: " + e.getMessage());
      }
    });
  }

  @Override
  public void enviar(Mensaje mensaje, String destinatario) {
    this.enviarAsync(mensaje, destinatario);
  }
}
