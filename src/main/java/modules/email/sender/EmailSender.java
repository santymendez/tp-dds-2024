package modules.email.sender;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Esta clase encapsula la funcionalidad de envío de correos electrónicos.
 * Se Realiza a través de un servidor SMTP configurado con Gmail.
 * Se recomienda utilizar el método enviar() para enviar un mensaje de correo.
 * electrónico especificando el destinatario y el mensaje a enviar.
 */

public class EmailSender {
  private final String nombreDeUsuario = Config.getUser();
  private final String contrasenia = Config.getApiKey(); // Acá se usa la API key
  private final Session sesion;

  private static final Logger logger = LogManager.getLogger(EmailSender.class);

  /**
   * Se configuran las propiedades del servidor.
   */
  public EmailSender() {
    // Configuración de las propiedades del servidor de correo
    Properties propiedadesDelServidorDeCorreo = new Properties();
    propiedadesDelServidorDeCorreo.put("mail.smtp.host", "smtp.gmail.com");
    propiedadesDelServidorDeCorreo.put("mail.smtp.auth", "true");
    propiedadesDelServidorDeCorreo.put("mail.smtp.port", "587");
    propiedadesDelServidorDeCorreo.put("mail.smtp.starttls.enable", "true");

    // Crear una sesión de correo electrónico autenticada
    sesion = Session.getInstance(propiedadesDelServidorDeCorreo, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(nombreDeUsuario, contrasenia);
      }
    });
  }

  /**
   * Se encarga de enviar el mensaje al destinatario. Caso de
   * que ocurriera un error se lanza una excepcion indicando por
   * que no se pudo realizar el envio
   *
   * @param mensajeAenviar el mensaje que se quiere enviar
   * @param destinatario   el destinatario al cual se quiere enviar el mensaje
   */


  public void enviar(Mensaje mensajeAenviar, String destinatario) {
    try {
      // Crear un mensaje de correo electrónico
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(nombreDeUsuario)); // Dirección de quien lo envia
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
      message.setSubject(mensajeAenviar.getAsunto());
      message.setText(mensajeAenviar.getCuerpo());

      // Enviar el mensaje
      Transport.send(message);

    } catch (MessagingException e) {
      logger.error("Error al enviar el correo electrónico: ", e);
      throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage());
    }
  }
}