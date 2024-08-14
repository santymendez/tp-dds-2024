package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;

/**
 * Interfaz Repositorio para los Sensores de Temperatura.
 */

public interface InterfaceSensoresTemperaturaRepository {
  Optional<SensorTemperatura> buscar(int id);

  void guardar(SensorTemperatura sensor);

  List<SensorTemperatura> obtenerSensores();
}
