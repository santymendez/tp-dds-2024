package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceVulnerablesRepository;

/**
 * Repositorio para los vulnerables.
 */

public class VulnerablesRepository implements InterfaceVulnerablesRepository,
    WithSimplePersistenceUnit {


  /**
   * Guarda uno o más colaboradores en la base de datos.
   *
   * @param vulnerables vulerables a guardar.
   */

  @Override
  public void guardar(Vulnerable... vulnerables) {
    withTransaction(() -> {
      for (Vulnerable vulnerable : vulnerables) {
        entityManager().persist(vulnerable);
      }
    });
  }

  @Override
  public void guardar(Vulnerable vulnerable) {
    withTransaction(() -> entityManager().persist(vulnerable));
  }

  @Override
  public void modificar(Vulnerable vulnerable) {
    entityManager().merge(vulnerable);
  }

  @Override
  public void eliminarFisico(Vulnerable vulnerable) {
    entityManager().remove(vulnerable);
  }

  @Override
  public void eliminar(Vulnerable vulnerable) {
    vulnerable.setActivo(false);
    entityManager().merge(vulnerable);
  }

  @Override
  public Optional<Vulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Vulnerable.class, id));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Vulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + Vulnerable.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }

}
