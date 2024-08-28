package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.MedicionSensor;
import models.repositories.interfaces.InterfaceMedicionesRepository;

/**
 * Repositorio para las Mediciones.
 */

public class MedicionesRepository implements InterfaceMedicionesRepository, WithSimplePersistenceUnit {
  public void guardar(MedicionSensor... mediciones) {
    withTransaction(() -> {
      for (MedicionSensor medicion : mediciones) {
        entityManager().persist(medicion);
      }
    });
  }

  public void guardar(MedicionSensor medicionSensor) {
    withTransaction(() -> {
      entityManager().persist(medicionSensor);
    });
  }

  public void modificar(MedicionSensor medicionSensor) {
    withTransaction(() -> {
      entityManager().merge(medicionSensor);
    });
  }

  public void eliminarFisico(MedicionSensor medicionSensor) {
    entityManager().remove(medicionSensor);
  }

  public void eliminar(MedicionSensor medicionSensor) {
    medicionSensor.setActivo(false);
    entityManager().merge(medicionSensor);
  }

  public Optional<MedicionSensor> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(MedicionSensor.class, id));
  }

  @SuppressWarnings("unchecked")
  public List<MedicionSensor> buscarTodos() {
    return entityManager()
        .createQuery("from " + MedicionSensor.class.getName())
        .getResultList();
  }
}
