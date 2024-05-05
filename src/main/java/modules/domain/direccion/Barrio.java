package modules.domain.direccion;

import lombok.Getter;
import lombok.Setter;


/**
 * Representa un barrio con un nombre, calle y número.
 */
@Getter
@Setter

public class Barrio {
  private String nombreBarrio;
  private String calle;
  private Integer numero;
}
