package models.entities.reporte.generador;

import java.util.List;
import models.entities.heladera.Heladera;
import models.repositories.interfaces.InterfaceHeladerasRepository;
import models.repositories.RepositoryLocator;

/**
 * CronJob para la generacion de reportes semanal.
 */

public class GeneradorReporteCronJob {

  /**
   * Genera el reporte semanal.
   */

  public static void main(String[] args) {
    InterfaceHeladerasRepository heladerasRepository =
        (InterfaceHeladerasRepository) RepositoryLocator
            .get("heladerasRepository");

    List<Heladera> heladeras = heladerasRepository.buscarTodas();

    GeneradorReporte generador = new GeneradorReporte();
    generador.generarReporte(heladeras);
  }
}
