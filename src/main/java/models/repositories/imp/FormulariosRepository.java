package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.formulario.Formulario;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceFormulariosRepository;

/**
 * Repositorio para los formularios.
 */

public class FormulariosRepository implements InterfaceFormulariosRepository,
    WithSimplePersistenceUnit {
  @Override
  public void guardar(Formulario... formularios) {
    withTransaction(() -> {
      for (Formulario formulario : formularios) {
        entityManager().persist(formulario);
      }
    });
  }

  @Override
  public void guardar(Formulario formulario) {
    withTransaction(() -> entityManager().persist(formulario));
  }

  @Override
  public void modificar(Formulario formulario) {
    entityManager().merge(formulario);
  }

  @Override
  public void eliminarFisico(Formulario formulario) {
    entityManager().remove(formulario);
  }

  @Override
  public void eliminar(Formulario formulario) {
    formulario.setActivo(false);
    entityManager().merge(formulario);
  }

  @Override
  public Optional<Formulario> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Formulario.class, id));
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Formulario> buscarTodos() {
    return entityManager()
        .createQuery("from " + Formulario.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }
}
