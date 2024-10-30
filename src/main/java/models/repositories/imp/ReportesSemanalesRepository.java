package models.repositories.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import models.entities.reporte.ReporteSemanal;

/**
 * Repositorio para los reportes semanales.
 */

public class ReportesSemanalesRepository extends GenericRepository {

  public void guardar(ReporteSemanal reporteSemanal) {
    super.guardar(reporteSemanal);
  }

  public void modificar(ReporteSemanal reporteSemanal) {
    super.modificar(reporteSemanal);
  }

  public void eliminarFisico(ReporteSemanal reporteSemanal) {
    super.eliminarFisico(reporteSemanal);
  }

  public void eliminar(ReporteSemanal reporteSemanal) {
    super.eliminar(reporteSemanal);
  }

  /**
   * Busca un reporte semanal por su id.
   *
   * @param id id del reporte semanal.
   * @return el reporte semanal.
   */

  public Optional<ReporteSemanal> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es null");
    }
    return super.buscarPorId(id, ReporteSemanal.class);
  }

  public List<ReporteSemanal> buscarTodos() {
    return super.buscarTodos(ReporteSemanal.class);
  }

  /**
   * Busca todos los reportes dentro del rango de fechas.
   *
   * @param desde fecha inicial.
   * @param hasta fecha final.
   * @return la lista de reportes semanales.
   */

  public List<ReporteSemanal> buscarTodosPorRangoDeFecha(LocalDate desde, LocalDate hasta) {
    return entityManager()
        .createQuery(
            "FROM ReporteSemanal r WHERE r.fecha BETWEEN :desde AND :hasta ORDER BY r.fecha DESC",
            ReporteSemanal.class
        )
        .setParameter("desde", desde)
        .setParameter("hasta", hasta)
        .getResultList();
  }
}
