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
public class VisitasRepository
        implements InterfaceVisitasRepository, WithSimplePersistenceUnit {

  /** Guarda una o varias visitas técnicas.
   *
   * @param visitas una o varias visitas técnicas.
   */
  public void guardar(VisitaTecnica... visitas) {
    withTransaction(() -> {
      for (VisitaTecnica visitaTecnica : visitas) {
        entityManager().persist(visitaTecnica);
      }
    });
  }

  /** Guarda una visita técnica en la base de datos.
   *
   * @param visitaTecnica una visita técnica.
   */

  public void guardar(VisitaTecnica visitaTecnica) {
    withTransaction(() -> entityManager().persist(visitaTecnica));
  }

  /** Modifica una visita técnica en la base de datos.
   *
   * @param visitaTecnica una visita técnica.
   */

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

  /** Busca todas las visitas técnicas.
   *
   * @return una lista con todas las visitas técnicas.
   */

  @SuppressWarnings("unchecked")
  public List<VisitaTecnica> buscarTodos() {
    return entityManager()
        .createQuery("from " + VisitaTecnica.class.getName())
        .getResultList();
  }
}
