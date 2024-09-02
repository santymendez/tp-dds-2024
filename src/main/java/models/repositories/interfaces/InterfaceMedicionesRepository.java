package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.MedicionSensor;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz que representa el repositorio de Mediciones.
 */

public interface InterfaceMedicionesRepository extends PersistenciaSimple {

  void guardar(MedicionSensor... mediciones);

  void guardar(MedicionSensor medicionSensor);

  void modificar(MedicionSensor medicionSensor);

  void eliminarFisico(MedicionSensor medicionSensor);

  void eliminar(MedicionSensor medicionSensor);

  Optional<MedicionSensor> buscarPorId(Long id);

  List<MedicionSensor> buscarTodos();
}
