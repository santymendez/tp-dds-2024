package modules.sender.channels;

import modules.sender.Destinatario;
import modules.sender.Mensaje;
import modules.sender.TipoDestinatario;
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

  public void enviar(Mensaje mensaje, Destinatario destinatario){
    String idTelegram = destinatario.obtenerMedidoContacto(TipoDestinatario.TELEGRAM);
    Long destinatarioId = Long.parseLong(idTelegram);
    String men = mensaje.aplanarMensaje();
    bot.sendText(destinatarioId, men);
  }
}
