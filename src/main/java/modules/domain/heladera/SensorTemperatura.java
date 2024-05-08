package modules.domain.heladera;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas maximas
 * y minimas.
 */

@Getter
@Setter //Permite recibir una nueva ultima temperatura
//Tambien permite modificar las temperaturas max y min
public class SensorTemperatura {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultimaTemperatura;

  public Boolean estaActiva() {
    return ultimaTemperatura < temperaturaMaxima && ultimaTemperatura > temperaturaMinima;
  }
}
