package models.entities.heladera;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
public class SensorMovimiento {
  private Heladera heladera;

  /**
   * Metodo que activa el sensor de movimiento y calcula los meses que estuvo activa.
   */

  public void activarSensor() {
    System.out.println("La heladera fue o esta siendo robada");
    this.heladera.setMesesActiva(this.heladera.calcularMesesActiva());
    this.heladera.setEstaActiva(false);
  }

  public void desactivarSensor() {
    this.heladera.setUltVezActivada(LocalDate.now());
    this.heladera.setEstaActiva(true);
  }
}