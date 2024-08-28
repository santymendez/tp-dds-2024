package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import models.entities.heladera.incidente.Incidente;
import models.repositories.interfaces.InterfaceIncidentesRepository;

/**
 * Repositorio para los Incidentes.
 */

public class IncidentesRepository implements InterfaceIncidentesRepository, WithSimplePersistenceUnit {

  public void guardar(Incidente... incidentes) {
    withTransaction(() -> {
      for (Incidente incidente : incidentes) {
        entityManager().persist(incidente);
      }
    });
  }

  public void guardar(Incidente incidente) {
    withTransaction(() -> {
      entityManager().persist(incidente);
    });
  }

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

  @SuppressWarnings("unchecked")
  public List<Incidente> buscarTodos() {
    return entityManager()
        .createQuery("from " + Incidente.class.getName())
        .getResultList();
  }
}
