package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;

/**
 * Repositorio de sensores de movimiento.
 */

public class SensoresMovimientoRepository extends GenericRepository {

  public void guardar(SensorMovimiento sensorMovimiento) {
    super.guardar((sensorMovimiento));
  }

  public void modificar(SensorMovimiento sensorMovimiento) {
    super.modificar(sensorMovimiento);
  }

  public void eliminarFisico(SensorMovimiento sensorMovimiento) {
    super.eliminarFisico(sensorMovimiento);
  }

  public void eliminar(SensorMovimiento sensorMovimiento) {
    super.eliminar(sensorMovimiento);
  }

  /**
   * Busca un sensor de movimiento por su id.
   *
   * @param id Id del sensor de movimiento a buscar.
   *
   * @return Un Optional con el sensor encontrado, o vacío si no se encontró.
   */

  public Optional<SensorMovimiento> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, SensorMovimiento.class);
  }

  public List<SensorMovimiento> buscarTodos() {
    return super.buscarTodos(SensorMovimiento.class);
  }


  /**
   * Busca un sensor de movimiento por el id de la heladera.
   *
   * @param idHeladera id de la heladera.
   * @return sensor de movimiento.
   */

  public Optional<SensorMovimiento> buscarPorHeladera(Long idHeladera) {
    return entityManager()
        .createQuery(
            "SELECT s FROM SensorMovimiento s WHERE s.heladera.id = :idHeladera",
            SensorMovimiento.class
        )
        .setParameter("idHeladera", idHeladera)
        .getResultList()
        .stream()
        .findFirst();
  }

}
