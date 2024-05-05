package modules.domain.personas.colaborador.reconocimiento;

import modules.domain.personas.colaborador.reconocimiento.formula.Formula;

/**
 * Representa el reconocimiento que recieben los colaboradores.
 */

public class Reconocimiento {
  private Float puntosActuales;
  private Float puntosUsados;

  private Formula formulaCalculoDePuntos;

  public Float calcularPuntos() {
    return 1.0f;
  }
}
