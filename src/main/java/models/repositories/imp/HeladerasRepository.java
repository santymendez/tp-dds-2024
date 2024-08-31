package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.Getter;
import models.entities.heladera.Heladera;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceHeladerasRepository;

/**
 * Repositorio para las Heladeras.
 */

@Getter
public class HeladerasRepository implements InterfaceHeladerasRepository,
        WithSimplePersistenceUnit {

  /**
   * Guarda las heladeras.
   *
   * @param heladeras heladeras a guardar.
   *
   */

  public void guardar(Heladera... heladeras) {
    withTransaction(() -> {
      for (Heladera heladera : heladeras) {
        entityManager().persist(heladera);
      }
    });
  }

  public void guardar(Heladera heladera) {
    withTransaction(() -> entityManager().persist(heladera));
  }

  /**
   * Modifica una heladera.
   *
   * @param heladera heladera a modificar.
   */

  public void modificar(Heladera heladera) {
    entityManager().merge(heladera);
  }

  public void eliminarFisico(Heladera heladera) {
    entityManager().remove(heladera);
  }

  public void eliminar(Heladera heladera) {
    heladera.setActivo(false);
    entityManager().merge(heladera);
  }

  public Optional<Heladera> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Heladera.class, id));
  }

  /**
   * Busca todas las heladeras.
   *
   * @return lista de heladeras.
   */

  @SuppressWarnings("unchecked")
  public List<Heladera> buscarTodos() {
    return entityManager()
        .createQuery("from " + Heladera.class.getName())
        .getResultList();
  }

}
