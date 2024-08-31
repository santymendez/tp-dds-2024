package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.direccion.Direccion;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceDireccionesRepository;

/**
 * Clase repositorio de direcciones.
 */

public class DireccionesRepository implements InterfaceDireccionesRepository,
    WithSimplePersistenceUnit {
  @Override
  public void guardar(Direccion... direcciones) {
    withTransaction(() -> {
      for (Direccion direccion : direcciones) {
        entityManager().persist(direccion);
      }
    });
  }

  @Override
  public void guardar(Direccion direccion) {
    withTransaction(() -> entityManager().persist(direccion));
  }

  @Override
  public void modificar(Direccion direccion) {
    entityManager().merge(direccion);
  }

  @Override
  public void eliminarFisico(Direccion direccion) {
    entityManager().remove(direccion);
  }

  @Override
  public void eliminar(Direccion direccion) {
    direccion.setActivo(false);
    entityManager().merge(direccion);
  }

  @Override
  public Optional<Direccion> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Direccion.class, id));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Direccion> buscarTodos() {
    return entityManager()
        .createQuery("from " + Direccion.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }

}
