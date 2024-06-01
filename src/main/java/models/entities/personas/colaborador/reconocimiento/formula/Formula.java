package models.entities.personas.colaborador.reconocimiento.formula;

import models.entities.colaboracion.Colaboracion;

/**
 * Interfaz que representa una formula de calculo de puntos.
 */

public interface Formula {
  public default Float calcularPuntosDe(Colaboracion unaColaboracion) {
    return 0f;
  }
}
