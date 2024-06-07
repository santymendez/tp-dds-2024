package modules.sender;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa la estructura de los mensajes que vamos a enviar con cualquier Sender.
 * Se compone de 2 Atributos, el asunto y el cuerpo del mensaje.
 */

@AllArgsConstructor
@Getter
public class Mensaje {
  private String asunto;
  private String cuerpo;

  public String aplanarMensaje() {
    return this.getAsunto() + "\n\n" + this.getCuerpo();
  }
}
