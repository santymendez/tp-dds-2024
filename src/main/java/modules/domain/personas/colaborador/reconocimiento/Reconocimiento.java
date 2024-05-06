package modules.domain.personas.colaborador.reconocimiento;

import modules.domain.colaboracion.Colaboracion;
import modules.domain.personas.colaborador.reconocimiento.formula.Formula;

/**
 * Representa el reconocimiento que recieben los colaboradores.
 */

public class Reconocimiento {
  private Float puntosActuales;
  private Float puntosUsados;

  private Formula formulaCalculoDePuntos;

  public void sumarPuntos(Colaboracion colaboracion) {
    puntosActuales += formulaCalculoDePuntos.calcularPuntosDe(colaboracion);
  }
}
