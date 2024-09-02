package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz Repositorio para los Sensores de Temperatura.
 */

public interface InterfaceSensoresTemperaturaRepository extends PersistenciaSimple {

  void guardar(SensorTemperatura... sensores);

  void guardar(SensorTemperatura sensorTemperatura);

  void modificar(SensorTemperatura sensorTemperatura);

  void eliminarFisico(SensorTemperatura sensorTemperatura);

  void eliminar(SensorTemperatura sensorTemperatura);

  Optional<SensorTemperatura> buscarPorId(Long id);

  List<SensorTemperatura> buscarTodos();
}
