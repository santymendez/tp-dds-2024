package models.entities.heladera.limitador;

import java.time.Duration;

/**
 * Clase que comprueba si una Duration es menor al Tiempo Limite.
 */

public class Limitador {
  private final UnidadTiempo unidad;
  private final Long tiempoLimite;

  public Limitador(UnidadTiempo unidad, Long tiempo) {
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

  public Float calcularTiempoEnUnidad(Duration duration) {
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
      case SEMANAS -> {
        return (float) duration.toDays() / 7;
      }
      case MESES -> {
        return (float) duration.toDays() / 30;
      }
      case ANIOS -> {
        return (float) duration.toDays() / 365;
      }
      default ->
        throw new RuntimeException("Unnsupported unit: " + this.unidad);
    }
  }
}
