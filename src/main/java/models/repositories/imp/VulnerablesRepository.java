package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.interfaces.InterfaceVulnerablesRepository;

/**
 * Repositorio para los vulnerables.
 */

public class VulnerablesRepository implements InterfaceVulnerablesRepository {


  /**
   * Guarda uno o más colaboradores en la base de datos.
   *
   * @param vulnerables vulerables a guardar.
   */

  public void guardar(Vulnerable... vulnerables) {
    withTransaction(() -> {
      for (Vulnerable vulnerable : vulnerables) {
        entityManager().persist(vulnerable);
      }
    });
  }

  public void guardar(Vulnerable vulnerable) {
    withTransaction(() -> entityManager().persist(vulnerable));
  }

  public void modificar(Vulnerable vulnerable) {
    entityManager().merge(vulnerable);
  }

  public void eliminarFisico(Vulnerable vulnerable) {
    entityManager().remove(vulnerable);
  }

  public void eliminar(Vulnerable vulnerable) {
    vulnerable.setActivo(false);
    entityManager().merge(vulnerable);
  }

  public Optional<Vulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Vulnerable.class, id));
  }

  /**
   * Busca todos los vulnerables.
   *
   * @return una lista con todos los vulnerables.
   */

  @SuppressWarnings("unchecked")
  public List<Vulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + Vulnerable.class.getName())
        .getResultList();
  }
}
