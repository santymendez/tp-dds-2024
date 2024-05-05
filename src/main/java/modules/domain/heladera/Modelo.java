package modules.domain.heladera;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa un modelo de Heladera. Tiene como atributos su modelo y un sensor de temperatura.
 */

@Getter
@Setter

public class Modelo {
  private String modelo;
  private SensorTemperatura sensorTemperatura;

  public Boolean estaActiva() {
    return sensorTemperatura.estaActiva();
  }
}
