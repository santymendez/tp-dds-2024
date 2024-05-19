package modules.domain.personas.contacto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa un contacto, puede ser un mail, un telefono fijo o un numero de celular.
 */

@Getter
@AllArgsConstructor
public class Contacto {
  private final String contacto;
  private final TipoContacto tipoContacto;
}