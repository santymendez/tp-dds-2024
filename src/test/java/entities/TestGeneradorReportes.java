package entities;

import config.RepositoryLocator;
import config.UtilsLocator;
import models.entities.heladera.Heladera;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ReporteSemanal;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.reportes.GeneradorReporte;
import java.util.List;

public class TestGeneradorReportes {
  @Test
  @DisplayName("Se genera un reporte semanal en la carpeta reportes")
  public void testGeneradorReportes() {
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
