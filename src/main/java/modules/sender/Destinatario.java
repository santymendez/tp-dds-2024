package modules.sender;

import java.util.HashMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Destinatario {
  private HashMap<TipoDestinatario, String> mediosDeContacto;
  // Alternativa por si quisieramos que tuviera mas de un nro de telefono, mail, etc.
  //private HashMap<TipoDestinatario, List<String>> mediosDeContacto;

  public void agregarMedioDeContacto(TipoDestinatario tipo, String contacto){
    if (mediosDeContacto == null) {
      mediosDeContacto = new HashMap<TipoDestinatario, String>();
    }

    mediosDeContacto.put(tipo, contacto);
  }

  public String obtenerMedidoContacto(TipoDestinatario tipo){
    return mediosDeContacto.get(tipo);
  }
}
