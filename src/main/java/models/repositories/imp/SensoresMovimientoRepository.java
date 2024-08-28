package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;
import models.repositories.interfaces.InterfaceSensoresMovimientoRepository;


/**
 * Repositorio para los Sensores de Movimiento.
 */

public class SensoresMovimientoRepository implements InterfaceSensoresMovimientoRepository, WithSimplePersistenceUnit {

  public void guardar(SensorMovimiento... sensores) {
    withTransaction(() -> {
      for (SensorMovimiento sensorMovimiento : sensores) {
        entityManager().persist(sensorMovimiento);
      }
    });
  }

  public void guardar(SensorMovimiento sensorMovimiento) {
    withTransaction(() -> {
      entityManager().persist(sensorMovimiento);
    });
  }

  public void modificar(SensorMovimiento sensor) {
    withTransaction(() -> {
      entityManager().merge(sensor);
    });
  }

  public void eliminarFisico(SensorMovimiento sensor) {
    entityManager().remove(sensor);
  }

  public void eliminar(SensorMovimiento sensor) {
    sensor.setActivo(false);
    entityManager().merge(sensor);
  }

  public Optional<SensorMovimiento> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(SensorMovimiento.class, id));
  }

  @SuppressWarnings("unchecked")
  public List<SensorMovimiento> buscarTodos() {
    return entityManager()
        .createQuery("from " + SensorMovimiento.class.getName())
        .getResultList();
  }

}


