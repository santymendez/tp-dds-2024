package config;

import java.util.HashMap;
import models.entities.personas.contacto.TipoContacto;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;
import utils.sender.channels.EmailSender;
import utils.sender.channels.TelegramBotSender;
import utils.sender.channels.WhatsAppSender;

/**
 * Service Locator para obtener los servicios de envío de mensajes.
 */

public class SenderLocator {
  private static final HashMap<TipoContacto, SenderInterface> instances = new HashMap<>();

  /**
   * Devuelve una instancia del Sender adecuado.
   *
   * @param tipoContacto Tipo de contacto.
   * @return Instancia del Sender adecuado.
   */

  public static SenderInterface instanceOf(TipoContacto tipoContacto) {
    if (!instances.containsKey(tipoContacto)) {
      if (tipoContacto.equals(TipoContacto.MAIL)) {
        instances.put(tipoContacto, EmailSender.getInstance());
      } else if (tipoContacto.equals(TipoContacto.WHATSAPP)) {
        instances.put(tipoContacto, WhatsAppSender.getInstance());
      } else if (tipoContacto.equals(TipoContacto.TELEGRAM)) {
        instances.put(tipoContacto, TelegramBotSender.getInstance());
      }
    }

    return instances.get(tipoContacto);
  }
}