package modules.domain.heladera;

/**
 * Representa un sensor de temperatura con la ultima temperatura, las temperaturas maximas y minimas.
 */

public class SensorTemperatura {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultimaTemperatura;

  public Boolean estaActiva() {
    return ultimaTemperatura < temperaturaMaxima && ultimaTemperatura > temperaturaMinima;
  }
}