package models.repositories.imp;

import java.time.LocalDate;
import java.util.List;
import models.entities.reporte.ReporteSemanal;

/**
 * Repositorio para los reportes semanales.
 */

public class ReportesSemanalesRepository extends GenericRepository {

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
            "FROM ReporteSemanal r WHERE r.fecha BETWEEN :desde AND :hasta",
            ReporteSemanal.class
        )
        .setParameter("desde", desde)
        .setParameter("hasta", hasta)
        .getResultList();
  }
}
