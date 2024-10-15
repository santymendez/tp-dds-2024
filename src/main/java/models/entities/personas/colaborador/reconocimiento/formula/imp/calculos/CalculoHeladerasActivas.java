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

    // El calculo se hace 1 vez al mes para todos los colaboradores,
    // entonces, si tiene un mes activa, comienza a sumar puntos.

    return colaboracion.getHacerseCargoHeladera()
        .getHeladeraColocada().calcularMesesActiva() > 0 ? coeficiente : 0;
  }
}
