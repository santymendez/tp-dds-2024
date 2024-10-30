package utils.sender.channels;

import config.Config;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Clase Bot para el Telegram Sender.
 */

public class Bot extends TelegramLongPollingBot {

  @Override
  public String getBotUsername() {
    return Config.getTelegramBotUser();
  }

  @Override
  public String getBotToken() {
    return Config.getTelegramBotToken();
  }

  @Override
  public void onUpdateReceived(Update update) {

    if (update.hasMessage() && update.getMessage().hasText()) {
      String messageText = update.getMessage().getText();
      Long chatId = update.getMessage().getChatId();

      if (messageText.equalsIgnoreCase("/start")) {
        sendText(chatId, "¡Bienvenido! ¿En qué puedo ayudarte?");
      } else if (messageText.equalsIgnoreCase("/register")) {
        // aca si te guardas chatId ya podrias hablar con la persona
        sendText(chatId, "ID Registrada: " + chatId);
      } else if (messageText.equalsIgnoreCase("/help")) {
        sendText(chatId,
            "Estos son los comandos disponibles:\n/register - Registrarse\n/help - Ayuda");
      } else {
        sendText(chatId, "El siguiente mensaje no es un comando valido: " + messageText);
      }
    }
  }

  /**
   * Metodo que envia un texto.
   *
   * @param who Destinatario.
   * @param what El motivo.
   */

  public void sendText(Long who, String what) {
    SendMessage sm = SendMessage.builder()
        .chatId(who.toString())
        .text(what).build();
    try {
      execute(sm);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }
}