package models.entities.personas.colaborador.reconocimiento.formula.imp.calculos;

import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.reconocimiento.formula.InterfazFormula;

/**
 * Cálculo como clase con su propio coeficiente y forma de cálculo.
 */

@Setter
public class CalculoPesosDonados implements InterfazFormula {
  private Float coeficiente = 0.5f;

  @Override
  public Float calcularPuntosDe(Colaboracion colaboracion) {
    return colaboracion.getDonacionDinero().getMonto() * coeficiente;
  }
}
