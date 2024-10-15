package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;

/**
 * Repositorio de sensores de temperatura.
 */

public class SensoresTemperaturaRepository extends GenericRepository {

  public void guardar(SensorTemperatura sensorTemperatura) {
    super.guardar((sensorTemperatura));
  }

  public void modificar(SensorTemperatura sensorTemperatura) {
    super.modificar(sensorTemperatura);
  }

  public void eliminarFisico(SensorTemperatura sensorTemperatura) {
    super.eliminarFisico(sensorTemperatura);
  }

  public void eliminar(SensorTemperatura sensorTemperatura) {
    super.eliminar(sensorTemperatura);
  }

  /**
   * Busca un sensor de temperatura por su id.
   *
   * @param id Id del sensor de temperatura a buscar.
   *
   * @return Un Optional con el sensor encontrado, o vacío si no se encontró.
   */

  public Optional<SensorTemperatura> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, SensorTemperatura.class);
  }

  public List<SensorTemperatura> buscarTodos() {
    return super.buscarTodos(SensorTemperatura.class);
  }


  /**
   * Busca un sensor de temperatura por el id de la heladera.
   *
   * @param idHeladera id de la heladera.
   * @return sensor de temperatura.
   */

  public Optional<SensorTemperatura> buscarPorHeladera(Long idHeladera) {
    return entityManager()
        .createQuery("SELECT s FROM SensorTemperatura s WHERE s.heladera.id = :idHeladera",
            SensorTemperatura.class)
        .setParameter("idHeladera", idHeladera)
        .getResultList()
        .stream()
        .findFirst();
  }
}
