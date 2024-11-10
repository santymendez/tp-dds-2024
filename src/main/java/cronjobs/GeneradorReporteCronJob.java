package cronjobs;

import config.RepositoryLocator;
import config.UtilsLocator;
import java.util.List;
import models.db.PersistenceUnitSwitcher;
import models.entities.heladera.Heladera;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ReporteSemanal;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import utils.reportes.GeneradorReporte;

/**
 * CronJob para la generacion de reportes semanal.
 */

public class GeneradorReporteCronJob implements Job {

  /**
   * Genera el reporte semanal.
   */

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {

    PersistenceUnitSwitcher.switchPersistenceUnit("cronjob-persistence-unit");

    ReportesHeladerasRepository reportesHeladerasRepository =
        RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);
    GenericRepository genericRepository =
        RepositoryLocator.instanceOf(GenericRepository.class);

    List<ReporteHeladera> reportesDeLaSemana =
        reportesHeladerasRepository.buscarTodosUltimaSemana();

    //Se genera el pdf de los reportes de esta semana
    GeneradorReporte generador = UtilsLocator.instanceOf(GeneradorReporte.class);
    String path = generador.generarReporte(reportesDeLaSemana);

    //Creo el reporte semanal
    ReporteSemanal reporteSemanal = new ReporteSemanal(path, reportesDeLaSemana);
    genericRepository.guardar(reporteSemanal);

    List<Heladera> heladeras = genericRepository.buscarTodos(Heladera.class);

    //Se crean nuevos reportes para una semana nueva.
    for (Heladera heladera : heladeras) {
      ReporteHeladera unReporte = new ReporteHeladera(heladera);
      reportesHeladerasRepository.guardar(unReporte);
    }
  }
}
