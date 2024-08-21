package models.repositories.interfaces;

import models.entities.heladera.sensores.MedicionSensor;

/**
 * Interfaz que representa el repositorio de Mediciones.
 */

public interface InterfaceMedicionesRepository {
  void guardar(MedicionSensor medicion);
}
