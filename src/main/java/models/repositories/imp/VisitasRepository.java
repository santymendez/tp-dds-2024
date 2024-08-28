package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.interfaces.InterfaceVisitasRepository;

/**
 * Repositorio para las Visitas Técnicas.
 */

@Getter
public class VisitasRepository implements InterfaceVisitasRepository, WithSimplePersistenceUnit {
  public void guardar(VisitaTecnica... visitas) {
    withTransaction(() -> {
      for (VisitaTecnica visitaTecnica : visitas) {
        entityManager().persist(visitaTecnica);
      }
    });
  }

  public void guardar(VisitaTecnica visitaTecnica) {
    withTransaction(() -> {
      entityManager().persist(visitaTecnica);
    });
  }

  public void modificar(VisitaTecnica visitaTecnica) {
    withTransaction(() -> {
      entityManager().merge(visitaTecnica);
    });
  }

  public void eliminarFisico(VisitaTecnica visitaTecnica) {
    entityManager().remove(visitaTecnica);
  }

  public void eliminar(VisitaTecnica visitaTecnica) {
    visitaTecnica.setActivo(false);
    entityManager().merge(visitaTecnica);
  }

  public Optional<VisitaTecnica> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(VisitaTecnica.class, id));
  }

  @SuppressWarnings("unchecked")
  public List<VisitaTecnica> buscarTodos() {
    return entityManager()
        .createQuery("from " + VisitaTecnica.class.getName())
        .getResultList();
  }
}
