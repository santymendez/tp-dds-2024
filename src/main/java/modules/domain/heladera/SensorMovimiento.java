package modules.domain.heladera;

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
  private Boolean sensorActivado = false;

  /**
   * Comprueba si el sensor está activado y debe alertar al sistema.
   *
   * @return Devuelve si la heladera se encuentra activa.
   */

  public Boolean estaActiva() {
    return !sensorActivado;
  }

  /**
   * Metodo que activa el sensor de movimiento y calcula los meses que estuvo activa.
   */

  public void activarSensor() {
    System.out.println("La heladera fue o esta siendo robada");
    sensorActivado = true;
    this.heladera.setMesesActiva(this.heladera.calcularMesesActiva());
  }

  public void desactivarSensor() {
    sensorActivado = false;
    this.heladera.setUltVezActivada(LocalDate.now());
  }
}