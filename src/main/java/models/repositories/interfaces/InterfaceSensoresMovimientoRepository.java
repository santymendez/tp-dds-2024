package models.repositories.interfaces;

import java.util.Optional;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;

/**
 * Interfaz Repositorio para los Sensores de Movimiento.
 */

public interface InterfaceSensoresMovimientoRepository {
  Optional<SensorMovimiento> buscar(int id);

  void guardar(SensorMovimiento sensor);
}
