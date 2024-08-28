package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;

/**
 * Interfaz Repositorio para los Sensores de Movimiento.
 */

public interface InterfaceSensoresMovimientoRepository {
  public void guardar(SensorMovimiento ... sensores);

  public void guardar(SensorMovimiento sensor);

  public void modificar(SensorMovimiento sensor);

  public void eliminarFisico(SensorMovimiento sensor);

  public void eliminar(SensorMovimiento sensor);

  public Optional<SensorMovimiento> buscarPorId(Long id);

  public List<SensorMovimiento> buscarTodos();
}
