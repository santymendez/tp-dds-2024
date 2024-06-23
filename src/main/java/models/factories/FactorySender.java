package models.factories;

import models.entities.personas.contacto.TipoContacto;
import utils.sender.SenderInterface;
import utils.sender.channels.EmailSender;
import utils.sender.channels.TelegramBotSender;
import utils.sender.channels.WhatsAppSender;

/**
 * Representa una clase factory que permite crear los Senders.
 */

public class FactorySender {

  /**
   * Instancia un Sender a partir de un Tipo de Contacto.
   *
   * @param tipoContacto El tipo de Contacto.
   * @return Instancia del Sender.
   */

  public static SenderInterface obtenerInstanciaSegun(TipoContacto tipoContacto) {
    switch (tipoContacto) {
      case MAIL -> {
        return EmailSender.getInstance();
      }
      case TELEGRAM -> {
        return TelegramBotSender.getInstance();
      }
      case WHATSAPP -> {
        return WhatsAppSender.getInstance();
      }
      default -> throw new RuntimeException("Error: Caso no contemplado");
    }
  }
}
