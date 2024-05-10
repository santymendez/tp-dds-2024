package modules.domain.tarjeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import modules.domain.personas.colaborador.Colaborador;
import modules.domain.personas.vulnerable.Vulnerable;

/**
 * Representa la información de registro que incluye al colaborador y al vulnerable.
 */

@Getter
@AllArgsConstructor
public class InformacionRegistro {
  private Colaborador colaborador;
  private Vulnerable vulnerable;
}
