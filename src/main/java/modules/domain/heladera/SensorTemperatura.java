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

  public Boolean estaActiva() {
    return ultimaTemperatura < temperaturaMaxima && ultimaTemperatura > temperaturaMinima;
  }

  /**
   * Metodo que activa el sensor de temperatura.
   */

  public void activarSensor() {
    this.heladera.setMesesActiva(this.heladera.calcularMesesActiva());
  }

  public void desactivarSensor() {
    this.heladera.setUltVezActivada(LocalDate.now());
  }
}
