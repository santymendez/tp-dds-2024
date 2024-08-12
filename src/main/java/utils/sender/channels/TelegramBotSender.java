package utils.sender.channels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Clase que representa el Telegram Bot Sender.
 */

public class TelegramBotSender implements  SenderInterface {
  private static Bot bot;
  private static TelegramBotSender instance;
  private static final Logger logger = LogManager.getLogger(TelegramBotSender.class);

  private TelegramBotSender() throws TelegramApiException {
    TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
    bot = new Bot();
    botsApi.registerBot(bot);
  }

  /**
   * Metodo para instanciar el TelegramBotSender.
   *
   * @return la instancia del TelegramBotSender.
   */

  public static TelegramBotSender getInstance() {
    try {
      if (instance == null) {
        instance = new TelegramBotSender();
      }
      return instance;
    } catch (TelegramApiException e) {
      logger.error("Error al obtener una instancia de TelegramBotSender", e);
      throw new RuntimeException("Error al obtener una instancia de TelegramBotSender");
    }
  }

  /**
   * Metodo para enviar el mensaje.
   *
   * @param mensaje Mensaje a enviar.
   * @param destinatario Destinatario.
   */

  public void enviar(Mensaje mensaje, String destinatario) {
    Long destinatarioId = Long.parseLong(destinatario);
    String men = mensaje.aplanarMensaje();
    bot.sendText(destinatarioId, men);
  }
}
