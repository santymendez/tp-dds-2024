package utils.sender;

import java.util.HashMap;
import models.entities.personas.contacto.TipoContacto;
import utils.sender.channels.EmailSender;
import utils.sender.channels.TelegramBotSender;
import utils.sender.channels.WhatsAppSender;

/**
 * Service Locator para poder obtener una instancia del Sender adecuado.
 */

public class SenderLocator {
  private static HashMap<TipoContacto, SenderInterface> services = new HashMap<>();

  static {
    services.put(TipoContacto.MAIL, EmailSender.getInstance());
    services.put(TipoContacto.WHATSAPP, WhatsAppSender.getInstance());
    services.put(TipoContacto.TELEGRAM, TelegramBotSender.getInstance());
  }

  public static SenderInterface getService(TipoContacto tipoContacto) {
    return services.get(tipoContacto);
  }

  public static void enviar(Mensaje mensaje, String destinatario, TipoContacto tipoContacto) {
    getService(tipoContacto).enviar(mensaje, destinatario);
  }
}