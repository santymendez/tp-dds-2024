package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;
import models.repositories.interfaces.InterfaceSensoresTemperaturaRepository;

/**
 * Repositorio para los Sensores de Temperatura.
 */

@Getter
public class SensoresTemperaturaRepository implements InterfaceSensoresTemperaturaRepository, WithSimplePersistenceUnit {
  public void guardar(SensorTemperatura... sensores) {
    withTransaction(() -> {
      for (SensorTemperatura sensorTemperatura : sensores) {
        entityManager().persist(sensorTemperatura);
      }
    });
  }

  public void guardar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> {
      entityManager().persist(sensorTemperatura);
    });
  }

  public void modificar(SensorTemperatura sensorTemperatura) {
    withTransaction(() -> {
      entityManager().merge(sensorTemperatura);
    });
  }

  public void eliminarFisico(SensorTemperatura sensorTemperatura) {
    entityManager().remove(sensorTemperatura);
  }

  public void eliminar(SensorTemperatura sensorTemperatura) {
    sensorTemperatura.setActivo(false);
    entityManager().merge(sensorTemperatura);
  }

  public Optional<SensorTemperatura> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(SensorTemperatura.class, id));
  }

  @SuppressWarnings("unchecked")
  public List<SensorTemperatura> buscarTodos() {
    return entityManager()
        .createQuery("from " + SensorTemperatura.class.getName())
        .getResultList();
  }
}
