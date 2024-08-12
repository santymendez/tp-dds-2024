package models.entities.heladera.sensores;

import java.time.LocalDateTime;
import lombok.Getter;
import models.entities.heladera.Heladera;

/**
 * Clase que representa los datos registrados al recibir una medición de temperatura.
 */

@Getter
public class MedicionSensor {
  private final Float valor;
  private final LocalDateTime fecha;
  private final Heladera heladera;

  /**
   * Metodo constructor de las mediciones de los sensores.
   *
   * @param valor representa el valor numerico tomado por el sensor en caso de ser.
   * @param heladera es la heladera que esta relacionada con la medicion.
   */

  public MedicionSensor(Float valor, Heladera heladera) {
    this.valor = valor;
    this.fecha = LocalDateTime.now();
    this.heladera = heladera;
  }
}
