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

public class ReportesRepository implements InterfaceReportesRepository, WithSimplePersistenceUnit {

  /**
   * Guarda un reporte de heladera en la base de datos.
   *
   * @param reportes Reportes de heladera a guardar.
   */

  public void guardar(ReporteHeladera... reportes) {
    withTransaction(() -> {
      for (ReporteHeladera reporte : reportes) {
        entityManager().persist(reporte);
      }
    });
  }

  /**
   * Guarda un reporte de heladera en la base de datos.
   *
   * @param reporte Reportes de heladera a guardar.
   */

  public void guardar(ReporteHeladera reporte) {
    withTransaction(() -> entityManager().persist(reporte));
  }

  /**
   * Modifica un reporte de heladera en la base de datos.
   *
   * @param reporte Reportes de heladera a modificar.
   */

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

  /**
   * Busca todos los reportes de heladera en la base de datos.
   *
   * @return Lista de reportes de heladera.
   */

  @SuppressWarnings("unchecked")
  public List<ReporteHeladera> buscarTodos() {
    return entityManager()
        .createQuery("from " + ReporteHeladera.class.getName())
        .getResultList();
  }

  /**
   * Busca todos los reportes de heladera en la base de datos.
   *
   * @return Lista de reportes de heladera.
   */

  public ReporteHeladera buscarSemanalPorHeladera(Long id) {
    LocalDate haceUnaSemana = LocalDate.now().minusWeeks(1);

    return entityManager()
        .createQuery(
            "SELECT r FROM ReporteHeladera r WHERE r.heladera.id = :id AND "
                + "r.fecha >= :haceUnaSemana",
            ReporteHeladera.class
        )
        .setParameter("id", id)
        .setParameter("haceUnaSemana", haceUnaSemana)
        .getSingleResult();
  }

  /**
   * Busca todos los reportes de heladera en la base de datos.
   *
   * @return Lista de reportes de heladera.
   */

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

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }
}
