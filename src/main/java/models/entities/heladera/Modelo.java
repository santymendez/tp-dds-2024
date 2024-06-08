package models.entities.heladera;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.sensores.SensorTemperatura;

/**
 * Representa un modelo de Heladera. Tiene como atributos su modelo y un sensor de temperatura.
 */

@Getter
@Setter
public class Modelo {
  private String modelo;
  private SensorTemperatura sensorTemperatura;
}
