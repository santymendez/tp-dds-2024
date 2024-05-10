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

  //TODO los pongo aca pero a lo mejor con los setters en el sensor basta
  public void nuevaTemperaturaMax(Float temperatura) {
    this.sensorTemperatura.setTemperaturaMaxima(temperatura);
  }

  public void nuevaTemperaturaMin(Float temperatura) {
    this.sensorTemperatura.setTemperaturaMinima(temperatura);
  } //Fin To Do
}
