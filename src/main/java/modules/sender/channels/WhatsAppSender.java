package modules.sender.channels;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import modules.sender.Config;
import modules.sender.Mensaje;

public class WhatsAppSender {
  private final String account_sid;
  private final String tokenAutenticacion;
  private final String nroEnvio;
  private static WhatsAppSender instance;

  private WhatsAppSender() {
    account_sid = Config.getWhatsappSid();
    tokenAutenticacion = Config.getWhatsappAuthToken();
    nroEnvio = "whatsapp:+" + Config.getWhatsappNumber();
  }

  public static WhatsAppSender getInstance(){
    if (instance == null){
      instance = new WhatsAppSender();
    }
    return instance;
  }

  public void enviar(Mensaje mensaje, String nroDest){
    String nroDestinatario = "whatsapp:+" + nroDest;
    String newMensaje = mensaje.aplanarMensaje();

    Twilio.init(account_sid, tokenAutenticacion);
      Message message = Message.creator(
              new com.twilio.type.PhoneNumber(nroDestinatario),
              new com.twilio.type.PhoneNumber(nroEnvio),
              newMensaje)
          .create();
  }

}
