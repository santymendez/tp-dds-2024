package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.heladera.incidente.Incidente;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceIncidentesRepository;

/**
 * Repositorio para los Incidentes.
 */

public class IncidentesRepository implements InterfaceIncidentesRepository,
        WithSimplePersistenceUnit {

  /**
   * Guarda los incidentes.
   *
   * @param incidentes incidentes a guardar.
   */

  public void guardar(Incidente... incidentes) {
    withTransaction(() -> {
      for (Incidente incidente : incidentes) {
        entityManager().persist(incidente);
      }
    });
  }

  /**
   * Guarda un incidente.
   *
   * @param incidente  incidente a guardar.
   */

  public void guardar(Incidente incidente) {
    withTransaction(() -> entityManager().persist(incidente));
  }

  /** Modifica un incidente.
   *
   * @param incidente incidente a modificar.
   */

  public void modificar(Incidente incidente) {
    withTransaction(() -> {
      entityManager().merge(incidente);
    });
  }

  public void eliminarFisico(Incidente incidente) {
    entityManager().remove(incidente);
  }

  public void eliminar(Incidente incidente) {
    incidente.setActivo(false);
    entityManager().merge(incidente);
  }

  public Optional<Incidente> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Incidente.class, id));
  }

  /**
   * Busca todos los incidentes.
   *
   * @return lista de incidentes.
   */

  @SuppressWarnings("unchecked")
  public List<Incidente> buscarTodos() {
    return entityManager()
        .createQuery("from " + Incidente.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }
}
