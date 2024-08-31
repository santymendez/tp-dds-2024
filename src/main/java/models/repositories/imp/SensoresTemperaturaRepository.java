package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.Getter;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceSensoresTemperaturaRepository;

/**
 * Repositorio para los Sensores de Temperatura.
 */

@Getter
public class SensoresTemperaturaRepository
        implements InterfaceSensoresTemperaturaRepository, WithSimplePersistenceUnit {
  /**
   * Guarda una lista de sensores de temperatura en la base de datos.
   *
   * @param sensores lista de instancias de SensorTemperatura que serán persistidas.
   */
  public void guardar(SensorTemperatura... sensores) {
    withTransaction(() -> {
      for (SensorTemperatura sensorTemperatura : sensores) {
        entityManager().persist(sensorTemperatura);
      }
    });
  }

  /**
   * Guarda un sensor de temperatura en la base de datos.
   *
   * @param sensorTemperatura la instancia de SensorTemperatura que será persistida.
   */
  public void guardar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> entityManager().persist(sensorTemperatura));
  }

  /**
   * Modifica un sensor de temperatura existente en la base de datos.
   *
   * @param sensorTemperatura la instancia de SensorTemperatura que será modificada.
   */
  public void modificar(SensorTemperatura sensorTemperatura) {
    entityManager().merge(sensorTemperatura);
  }

  /**
   * Elimina físicamente un sensor de temperatura de la base de datos.
   *
   * @param sensorTemperatura la instancia de  SensorTemperatura que será eliminada.
   */
  public void eliminarFisico(SensorTemperatura sensorTemperatura) {
    entityManager().remove(sensorTemperatura);
  }

  /**
   * Elimina lógicamente un sensor de temperatura, desactivándolo en la base de datos.
   *
   * @param sensorTemperatura la instancia de SensorTemperatura que será desactivada.
   */
  public void eliminar(SensorTemperatura sensorTemperatura) {
    sensorTemperatura.setActivo(false);
    entityManager().merge(sensorTemperatura);
  }

  /**
   * Busca un sensor de temperatura por su ID.
   *
   * @param id el ID del SensorTemperatura a buscar.
   * @return un Optional que contendrá la instancia de SensorTemperatura si existe.
   */
  public Optional<SensorTemperatura> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(SensorTemperatura.class, id));
  }

  /**
   * Busca y devuelve todos los sensores de temperatura almacenados en la base de datos.
   *
   * @return una lista de instancias de SensorTemperatura.
   */
  @SuppressWarnings("unchecked")
  public List<SensorTemperatura> buscarTodos() {
    return entityManager()
            .createQuery("from " + SensorTemperatura.class.getName())
            .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }
}
