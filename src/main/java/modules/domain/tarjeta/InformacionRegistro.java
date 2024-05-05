package modules.domain.tarjeta;

import lombok.Getter;
import lombok.Setter;
import modules.domain.personas.vulnerable.Vulnerable;

/**
 * Representa la información de registro que incluye al colaborador y al vulnerable.
 */

@Getter
@Setter
public class InformacionRegistro {
  //private PersonaFisica colaborador;
  private Vulnerable vulnerable;
}
