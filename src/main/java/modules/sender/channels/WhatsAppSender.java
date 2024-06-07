package modules.sender.channels;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import modules.sender.Config;
import modules.sender.Destinatario;
import modules.sender.Mensaje;
import modules.sender.TipoDestinatario;

/**
 * Clase que representa el WhatsAppSender.
 */

public class WhatsAppSender {
  private final String accountSid;
  private final String tokenAutenticacion;
  private final String nroEnvio;
  private static WhatsAppSender instance;

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

  public void enviar(Mensaje mensaje, Destinatario destinatario) throws Exception {
    String nroDest = destinatario.obtenerMedidoContacto(TipoDestinatario.WHATSAPP);
    String nroDestinatario = "whatsapp:+" + nroDest;
    String newMensaje = mensaje.aplanarMensaje();

    Twilio.init(accountSid, tokenAutenticacion);
    Message message = Message.creator(
              new com.twilio.type.PhoneNumber(nroDestinatario),
              new com.twilio.type.PhoneNumber(nroEnvio),
              newMensaje)
          .create();
  }

}
