package modules.sender.channels;

import modules.sender.Mensaje;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotSender {
  private static Bot bot;
  private static TelegramBotSender instance;

  private TelegramBotSender() throws TelegramApiException {
    TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
    bot = new Bot();
    botsApi.registerBot(bot);
  }

  public static TelegramBotSender getInstance() throws TelegramApiException {
    if (instance == null) {
      instance = new TelegramBotSender();
    }
    return instance;
  }

  public void enviar(Long destinatario, Mensaje mensaje){
    String men = mensaje.aplanarMensaje();
    bot.sendText(destinatario, men);
  }
}
