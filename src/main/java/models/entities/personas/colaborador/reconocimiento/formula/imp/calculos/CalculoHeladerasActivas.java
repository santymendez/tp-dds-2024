package models.entities.personas.colaborador.reconocimiento.formula.imp.calculos;

import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.reconocimiento.formula.InterfazFormula;

/**
 * Cálculo como clase con su propio coeficiente y forma de cálculo.
 */

@Setter
public class CalculoHeladerasActivas implements InterfazFormula {
  private Float coeficiente = 5f;

  @Override
  public Float calcularPuntosDe(Colaboracion colaboracion) {

    // La primera vez que se llama a calcularMesesActiva() se calcula la cantidad de meses que
    // estuvo activa la heladera.

    Heladera heladera = colaboracion.getHacerseCargoHeladera().getHeladeraColocada();

    if (heladera.recienCargadaEnSistema()) {
      return colaboracion.getHacerseCargoHeladera().getHeladeraColocada()
          .calcularMesesActiva() * coeficiente;
    } else {
      return heladera.estuvoActivaElUltimoMes() ? coeficiente : 0f;
    }
  }
}
