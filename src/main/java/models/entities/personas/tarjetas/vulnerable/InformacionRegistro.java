package models.entities.personas.tarjetas.vulnerable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Representa la información de registro que incluye al colaborador y al vulnerable.
 */

@Getter
@AllArgsConstructor
public class InformacionRegistro {
  private Colaborador colaborador;
  private Vulnerable vulnerable;
}
