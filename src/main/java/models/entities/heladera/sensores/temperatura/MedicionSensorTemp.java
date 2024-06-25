package models.entities.heladera.sensores.temperatura;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Getter;

/**
 * Clase que representa los datos registrados al recibir una medición de temperatura.
 */

@Getter
public class MedicionSensorTemp {
  private final Float temperatura;
  private final LocalDateTime fecha;

  public MedicionSensorTemp(Float temperatura) {
    this.temperatura = temperatura;
    this.fecha = LocalDateTime.now();
  }

  //TODO aca les dejo una manera de comparar que encontre
  public Long periodoEnMinutos() {
    LocalDateTime instanteActual = LocalDateTime.now();
    return ChronoUnit.MINUTES.between(this.fecha, instanteActual);
  }

  public Boolean pasaronMenosDe5Min() {
    return this.periodoEnMinutos() < 5;
  }

}
