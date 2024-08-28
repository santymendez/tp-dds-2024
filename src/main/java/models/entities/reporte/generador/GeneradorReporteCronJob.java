package models.entities.reporte.generador;

import java.util.List;
import models.entities.heladera.Heladera;
import models.repositories.RepositoryLocator;
import models.repositories.imp.HeladerasRepository;
import models.repositories.interfaces.InterfaceHeladerasRepository;

/**
 * CronJob para la generacion de reportes semanal.
 */

public class GeneradorReporteCronJob {

  /**
   * Genera el reporte semanal.
   */

  public static void main(String[] args) {
    InterfaceHeladerasRepository heladerasRepository =
        RepositoryLocator
            .get("heladerasRepository", HeladerasRepository.class);

    List<Heladera> heladeras = heladerasRepository.buscarTodos();

    GeneradorReporte generador = new GeneradorReporte();
    generador.generarReporte(heladeras);
  }
}
