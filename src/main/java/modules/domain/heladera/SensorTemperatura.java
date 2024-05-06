package modules.domain.heladera;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de temperatura con la ultima temperatura, las temperaturas maximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultimaTemperatura;

  public Boolean estaActiva() {
    return ultimaTemperatura < temperaturaMaxima && ultimaTemperatura > temperaturaMinima;
  }
}