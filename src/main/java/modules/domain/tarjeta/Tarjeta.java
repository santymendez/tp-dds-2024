package modules.domain.tarjeta;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una tarjeta con un código, cantidad de usos, registros de
 * uso e información de registro.
 */

@Getter
@Setter
public class Tarjeta {
  private String codigo;
  private Integer cantidadDeUsos;
  private Set<RegistroUso> registroUsos;
  private InformacionRegistro informacionRegistro;

  /*public void actualizarCantidadUsos() {
    //TODO
  } */
}
