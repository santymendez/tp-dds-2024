package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.direccion.Barrio;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceBarriosRepository;

/**
 * Repositorio de barrios.
 */

public class BarriosRepository implements InterfaceBarriosRepository,
    WithSimplePersistenceUnit {
  @Override
  public void guardar(Barrio... barrios) {
    withTransaction(() -> {
      for (Barrio barrio : barrios) {
        entityManager().persist(barrio);
      }
    });
  }

  @Override
  public void guardar(Barrio barrio) {
    withTransaction(() -> entityManager().persist(barrio));
  }

  @Override
  public void modificar(Barrio barrio) {
    entityManager().merge(barrio);
  }

  @Override
  public void eliminarFisico(Barrio barrio) {
    entityManager().remove(barrio);
  }

  @Override
  public void eliminar(Barrio barrio) {
    barrio.setActivo(false);
    entityManager().merge(barrio);
  }

  @Override
  public Optional<Barrio> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Barrio.class, id));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Barrio> buscarTodos() {
    return entityManager()
        .createQuery("from " + Barrio.class.getName())
        .getResultList();
  }

}
