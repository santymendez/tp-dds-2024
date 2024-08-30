package models.entities.reporte.generador;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.reporte.ReporteHeladera;
import models.repositories.RepositoryLocator;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.interfaces.InterfaceHeladerasRepository;
import models.repositories.interfaces.InterfaceReportesRepository;

/**
 * CronJob para la generacion de reportes semanal.
 */

public class GeneradorReporteCronJob {

  /**
   * Genera el reporte semanal.
   */

  public static void main(String[] args) {
    InterfaceReportesRepository reportesRepository =
        RepositoryLocator
            .get("reportesRepository", ReportesRepository.class);
    List<ReporteHeladera> reportesDeLaSemana = reportesRepository.buscarTodosUltimaSemana();

    //Se genera el pdf de los reportes de esta semana
    GeneradorReporte generador = new GeneradorReporte();
    String path = generador.generarReporte(reportesDeLaSemana);

    for (ReporteHeladera reporte : reportesDeLaSemana) {
      reporte.setPath(path);
      reportesRepository.modificar(reporte);
    }

    InterfaceHeladerasRepository heladerasRepository =
        RepositoryLocator
            .get("heladerasRepository", HeladerasRepository.class);
    List<Heladera> heladeras = heladerasRepository.buscarTodos();

    //Se crean nuevos reportes para una semana nueva.
    for (Heladera heladera : heladeras) {
      ReporteHeladera unReporte = new ReporteHeladera(heladera);
      reportesRepository.guardar(unReporte);
    }
  }
}
