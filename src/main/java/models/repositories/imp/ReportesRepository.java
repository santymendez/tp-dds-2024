package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.reporte.ReporteHeladera;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceReportesRepository;

/**
 * Clase que representa a los repositorios de reportes de heladeras.
 */

//TODO todos los metodos
public class ReportesRepository implements InterfaceReportesRepository, WithSimplePersistenceUnit {

  public void guardar(ReporteHeladera... reportes) {
    withTransaction(() -> {
      for (ReporteHeladera reporte : reportes) {
        entityManager().persist(reporte);
      }
    });
  }

  public void guardar(ReporteHeladera reporte) {
    withTransaction(() -> {
      entityManager().persist(reporte);
    });
  }

  public void modificar(ReporteHeladera reporte) {
    entityManager().merge(reporte);
  }

  public void eliminarFisico(ReporteHeladera reporte) {
    entityManager().remove(reporte);
  }

  public void eliminar(ReporteHeladera reporte) {
    reporte.setActivo(false);
    entityManager().merge(reporte);
  }

  public Optional<ReporteHeladera> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(ReporteHeladera.class, id));
  }

  @SuppressWarnings("unchecked")
  public List<ReporteHeladera> buscarTodos() {
    return entityManager()
        .createQuery("from " + ReporteHeladera.class.getName())
        .getResultList();
  }

  public ReporteHeladera buscarSemanalPorHeladera(Long id) {
    LocalDate haceUnaSemana = LocalDate.now().minusWeeks(1);

    return entityManager()
        .createQuery(
            "SELECT r FROM ReporteHeladera r WHERE r.heladera.id = :id AND r.fecha >= :haceUnaSemana",
            ReporteHeladera.class
        )
        .setParameter("id", id)
        .setParameter("haceUnaSemana", haceUnaSemana)
        .getSingleResult();
  }

  public List<ReporteHeladera> buscarTodosUltimaSemana() {
    LocalDate haceUnaSemana = LocalDate.now().minusWeeks(1);

    return entityManager()
        .createQuery(
            "SELECT r FROM ReporteHeladera r WHERE r.fecha >= :haceUnaSemana",
            ReporteHeladera.class
        )
        .setParameter("haceUnaSemana", haceUnaSemana)
        .getResultList();
  }

  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }
}
