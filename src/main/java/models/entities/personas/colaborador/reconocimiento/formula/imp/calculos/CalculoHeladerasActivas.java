package models.entities.personas.colaborador.reconocimiento.formula.imp.calculos;

import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.reconocimiento.formula.InterfazFormula;

/**
 * Cálculo como clase con su propio coeficiente y forma de cálculo.
 */

@Setter
public class CalculoHeladerasActivas implements InterfazFormula {
  private Float coeficiente = 5f;

  @Override
  public Float calcularPuntosDe(Colaboracion colaboracion) {

    //TODO ESTA MAL EL CALCULO:
    // HAY QUE SUMAR LOS MESES ACTIVAS DE CADA HELADERA Y ESO MULTIPLICARLO POR EL COEFICIENTE

    return colaboracion.tiempoActivaHeladera() * coeficiente;
  }
}
