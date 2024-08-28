package models.entities.heladera.limitador;

import java.time.Duration;

/**
 * Clase que comprueba si una Duration es menor al Tiempo Limite.
 */

public class Limitador {
  private final UnidadTiempo unidad;
  private final long tiempoLimite;

  public Limitador(UnidadTiempo unidad, long tiempo) {
    this.unidad = unidad;
    this.tiempoLimite = tiempo;
  }

  public Boolean menorAlLimite(Duration duration) {
    return this.calcularTiempoEnUnidad(duration) < tiempoLimite;
  }

  /**
   * Convierte la Duracion al Tipo de Unidad establecido.
   *
   * @param duration tiempo transcurrido.
   * @return tiempo en la unidad establecida.
   */

  public float calcularTiempoEnUnidad(Duration duration) {
    switch (this.unidad) {
      case SEGUNDOS -> {
        return (float) duration.toSeconds();
      }
      case MINUTOS -> {
        return (float) duration.toMinutes();
      }
      case HORAS -> {
        return (float) duration.toHours();
      }
      case DIAS -> {
        return (float) duration.toDays();
      }
      default -> {
        throw new RuntimeException("Unnsupported unit: " + this.unidad);
      }
    }
  }
}
