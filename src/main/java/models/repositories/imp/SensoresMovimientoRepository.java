package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceSensoresMovimientoRepository;


/**
 * Repositorio para los Sensores de Movimiento.
 */

public class SensoresMovimientoRepository
        implements InterfaceSensoresMovimientoRepository, WithSimplePersistenceUnit {

  /**
   * Guarda una lista de sensores de movimiento en la base de datos.
   *
   * @param sensores lista de instancias de SensorMovimiento} que serán persistidas.
   */

  public void guardar(SensorMovimiento... sensores) {
    withTransaction(() -> {
      for (SensorMovimiento sensorMovimiento : sensores) {
        entityManager().persist(sensorMovimiento);
      }
    });
  }

  /**
   * Guarda un sensor de movimiento en la base de datos.
   *
   * @param sensorMovimiento la instancia de SensorMovimiento que será persistida.
   */

  public void guardar(SensorMovimiento sensorMovimiento) {
    withTransaction(() -> entityManager().persist(sensorMovimiento));
  }

  /**
   *  un sensor de movimiento existente en la base de datos.
   *
   * @param sensor la instancia de SensorMovimiento que será modificada.
   */

  public void modificar(SensorMovimiento sensor) {
    entityManager().merge(sensor);
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

  /**
   * Busca todos los sensores de movimiento en la base de datos.
   *
   * @return una lista con todas las instancias de SensorMovimiento en la base de datos.
   */

  @SuppressWarnings("unchecked")
  public List<SensorMovimiento> buscarTodos() {
    return entityManager()
        .createQuery("from " + SensorMovimiento.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }

}


