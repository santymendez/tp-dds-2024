package modules.domain.heladera;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas maximas
 * y minimas.
 */

@Getter
@Setter //Permite recibir una nueva última temperatura
//También permite modificar las temperaturas max y min
public class SensorTemperatura {
  private Heladera heladera;
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultimaTemperatura;
  private Boolean sensorActivado = false;

  public Boolean estaActiva() {
    return !sensorActivado;
  }

  /**
   * Metodo que activa el sensor de temperatura.
   */

  public void activarSensor() {
    sensorActivado = true;
    this.heladera.calcularMesesActiva();
  }

  public void desactivarSensor() {
    sensorActivado = false;
    this.heladera.setUltVezInactiva(LocalDate.now());
  }
}
