package models.entities.reporte.generador;

import config.RepositoryLocator;
import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesRepository;

/**
 * CronJob para la generacion de reportes semanal.
 */

public class GeneradorReporteCronJob {

  /**
   * Genera el reporte semanal.
   */

  public static void main(String[] args) {
    ReportesRepository reportesRepository =
        RepositoryLocator
            .instanceOf(ReportesRepository.class);

    List<ReporteHeladera> reportesDeLaSemana = reportesRepository.buscarTodosUltimaSemana();

    //Se genera el pdf de los reportes de esta semana
    GeneradorReporte generador = new GeneradorReporte();
    String path = generador.generarReporte(reportesDeLaSemana);

    for (ReporteHeladera reporte : reportesDeLaSemana) {
      reporte.setPath(path);
      reportesRepository.modificar(reporte);
    }

    GenericRepository heladerasRepository =
        RepositoryLocator
            .instanceOf(GenericRepository.class);

    List<Heladera> heladeras = heladerasRepository.buscarTodos(Heladera.class);

    //Se crean nuevos reportes para una semana nueva.
    for (Heladera heladera : heladeras) {
      ReporteHeladera unReporte = new ReporteHeladera(heladera);
      reportesRepository.guardar(unReporte);
    }
  }
}
