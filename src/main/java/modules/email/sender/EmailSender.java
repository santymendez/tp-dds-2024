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

/**
 * Esta clase encapsula la funcionalidad de envío de correos electrónicos.
 * Se Realiza a través de un servidor SMTP configurado con Gmail.
 * Se recomienda utilizar el método enviar() para enviar un mensaje de correo
 * electrónico especificando el destinatario y el mensaje a enviar.
 */

public class EmailSender {
  private final Properties propiedadesDelServidorDeCorreo;
  private final String nombreDeUsuario = "heladeras.noreply@gmail.com";
  private final String contrasenia = "INSERTAR CONTRASENIA AQUI";
  private final Session sesion;

  public EmailSender(){
    // Configuración de las propiedades del servidor de correo
    propiedadesDelServidorDeCorreo = new Properties();
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
   * @param mensajeAEnviar el mensaje que se quiere enviar
   * @param destinatario el destinatario al cual se quiere enviar el mensaje
   */


  public void enviar(Mensaje mensajeAEnviar, String destinatario) {

    try {
      // Crear un mensaje de correo electrónico
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(nombreDeUsuario)); // Dirección de quien lo envia
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
      message.setSubject(mensajeAEnviar.getAsunto());
      message.setText(mensajeAEnviar.getCuerpo());

      // Enviar el mensaje
      Transport.send(message);

    } catch (MessagingException e) {
      e.printStackTrace();
      throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage());
    }
  }
}
