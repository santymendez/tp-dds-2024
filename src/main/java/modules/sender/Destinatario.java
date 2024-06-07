package modules.sender;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Destinatario {
  private String direccionMail;
  private String nroTelefonico;
  private Long telegramId;

  /*
  podria ser la alternativa para que cumplieran todos los senders la interfaz
  enviar(Mensaje men, Destinatario dest);
   */
}
