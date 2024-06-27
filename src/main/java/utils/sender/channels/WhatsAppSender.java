package utils.sender.channels;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.sender.Config;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Clase que representa el WhatsAppSender para el envio de WhatsApp a destinatarios.
 * (Twilio cobra 0,1 US$ por mensaje en la sandbox. Por lo tanto solo usar en la presentacion).
 */

public class WhatsAppSender implements SenderInterface {
  private final String accountSid;
  private final String tokenAutenticacion;
  private final String nroEnvio;
  private static WhatsAppSender instance;
  private static final Logger logger = LogManager.getLogger(WhatsAppSender.class);

  private WhatsAppSender() {
    accountSid = Config.getWhatsappSid();
    tokenAutenticacion = Config.getWhatsappAuthToken();
    nroEnvio = "whatsapp:+" + Config.getWhatsappNumber();
  }

  /**
   * Metodo para instanciar el WhatsAppSender.
   *
   * @return la instancia del WhatsAppSender.
   */

  public static WhatsAppSender getInstance() {
    if (instance == null) {
      instance = new WhatsAppSender();
    }
    return instance;
  }

  /**
   * Metodo para enviar un mensaje.
   *
   * @param mensaje Mensaje a enviar.
   * @param destinatario Destinatario.
   */

  public void send(Mensaje mensaje, String destinatario) throws Exception {
    String nroDestinatario = "whatsapp:+" + destinatario;
    String newMensaje = mensaje.aplanarMensaje();

    Twilio.init(accountSid, tokenAutenticacion);
    Message message = Message.creator(
              new com.twilio.type.PhoneNumber(nroDestinatario),
              new com.twilio.type.PhoneNumber(nroEnvio),
              newMensaje)
          .create();
  }

  /**
   * Se inicializa la heladera (Dar de alta).
   *
   * @param mensaje             mensaje para enviar.
   * @param destinatario        destinatario a quien enviar.
   */

  public void enviar(Mensaje mensaje, String destinatario) {
    try {
      this.send(mensaje, destinatario);
    } catch (Exception e) {
      logger.error("Error al enviar un mensaje de Whatsapp", e);
      throw new RuntimeException("Error al enviar un mensaje de Whatsapp");
    }
  }

}
