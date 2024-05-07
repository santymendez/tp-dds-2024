package modules.domain.personas.colaborador.reconocimiento;

import lombok.Getter;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;
import modules.domain.personas.colaborador.reconocimiento.formula.Formula;

/**
 * Representa el reconocimiento que reciben los colaboradores.
 */

public class Reconocimiento {
  @Getter
  private Float puntosPorColaborar;
  @Setter
  private Formula formulaCalculoDePuntos;

  public Reconocimiento() {
    this.puntosPorColaborar = 0f;
  }

  public void sumarPuntos(Colaboracion colaboracion) {
    puntosPorColaborar += formulaCalculoDePuntos.calcularPuntosDe(colaboracion);
  }

  public void restarPuntos(Float puntos) {
    puntosPorColaborar -= puntos;
  }
}