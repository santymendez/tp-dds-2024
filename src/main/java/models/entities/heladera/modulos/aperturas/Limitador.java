package models.entities.heladera.modulos.aperturas;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Clase que comprueba si una Duration es menor al Tiempo Limite.
 */

public class Limitador {
  private final ChronoUnit unidad;
  private final Float tiempoLimite;

  public Limitador(ChronoUnit unidad, Float tiempo) {
    this.unidad = unidad;
    this.tiempoLimite = tiempo;
  }

  public Boolean menorAlLimite(Duration duration) {
    return duration.get(unidad) < tiempoLimite;
  }
}
