package modules.domain.heladera;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
public class SensorMovimiento {
  private Boolean sensorActivado = false;
  //TODO chequear si les parece inicializar en false

  /**
   * Comprueba si el sensor está activado y debe alertar al sistema.
   *
   * @return Devuelve si la heladera se encuentra activa.
   */

  public Boolean estaActiva() {
    return !sensorActivado;
  }

  public void activarSensor() {
    System.out.println("La heladera fue o esta siendo robada");
    sensorActivado = true;
  }
}