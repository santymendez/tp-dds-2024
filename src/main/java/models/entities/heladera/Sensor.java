package models.entities.heladera;

/**
 * Interfaz que representa un sensor.
 */

public interface Sensor {
  void activarSensor(Heladera heladera);

  void activarHeladera(Heladera heladera);

  void desactivarHeladera(Heladera heladera);
}
