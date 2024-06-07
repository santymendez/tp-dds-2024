package modules.sender;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa la estructura de los mensajes que vamos a enviar con EmailSender.
 * Se compone de 2 Atributos, el asunto y el cuerpo del mensaje.
 */

@AllArgsConstructor
@Getter
public class Mensaje {
  String asunto;
  String cuerpo;
}
