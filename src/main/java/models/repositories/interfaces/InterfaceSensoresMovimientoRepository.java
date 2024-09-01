package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;

/**
 * Interfaz Repositorio para los Sensores de Movimiento.
 */

public interface InterfaceSensoresMovimientoRepository extends PersistenciaSimple {

  void guardar(SensorMovimiento ... sensores);

  void guardar(SensorMovimiento sensor);

  void modificar(SensorMovimiento sensor);

  void eliminarFisico(SensorMovimiento sensor);

  void eliminar(SensorMovimiento sensor);

  Optional<SensorMovimiento> buscarPorId(Long id);

  List<SensorMovimiento> buscarTodos();
}
