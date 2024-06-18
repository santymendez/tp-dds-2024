package utils.sender;

import java.util.HashMap;
import lombok.NoArgsConstructor;

/**
 * Clase destintatario.
 */

@NoArgsConstructor
public class Destinatario {
  private HashMap<TipoDestinatario, String> mediosDeContacto;
  // Alternativa por si quisieramos que tuviera mas de un nro de telefono, mail, etc.
  //private HashMap<TipoDestinatario, List<String>> mediosDeContacto;

  /**
   * Método que agrega el metodo de contacto.
   *
   * @param tipo El tipo de destinatario.
   * @param contacto El contacto como string.
   */

  public void agregarMedioDeContacto(TipoDestinatario tipo, String contacto) {
    if (mediosDeContacto == null) {
      mediosDeContacto = new HashMap<>();
    }

    mediosDeContacto.put(tipo, contacto);
  }

  public String obtenerMedidoContacto(TipoDestinatario tipo) {
    return mediosDeContacto.get(tipo);
  }
}
