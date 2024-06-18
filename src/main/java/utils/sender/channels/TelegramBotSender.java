package utils.sender.channels;

import models.entities.personas.contacto.TipoContacto;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.sender.Destinatario;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Clase que representa el Telegram Bot Sender.
 */

public class TelegramBotSender implements  SenderInterface {
  private static Bot bot;
  private static TelegramBotSender instance;

  private TelegramBotSender() throws TelegramApiException {
    TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
    bot = new Bot();
    botsApi.registerBot(bot);
  }

  /**
   * Método para instanciar el TelegramBotSender.
   *
   * @return la instancia del TelegramBotSender.
   * @throws TelegramApiException ni idea.
   */

  public static TelegramBotSender getInstance() throws TelegramApiException {
    if (instance == null) {
      instance = new TelegramBotSender();
    }
    return instance;
  }

  /**
   * Método para enviar el mensaje.
   *
   * @param mensaje Mensaje a enviar.
   * @param destinatario Destinatario.
   */

  public void enviar(Mensaje mensaje, Destinatario destinatario) {
    String idTelegram = destinatario.obtenerMedidoContacto(TipoContacto.TELEGRAM);
    Long destinatarioId = Long.parseLong(idTelegram);
    String men = mensaje.aplanarMensaje();
    bot.sendText(destinatarioId, men);
  }
}
