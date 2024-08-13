package models.entities.reporte.generador;

import models.repositories.heladera.InterfaceHeladerasRepository;
import utils.RepositoryLocator;

/**
 * CronJob para la generacion de reportes semanal.
 */

public class GeneradorReporteCronJob {

  /**
   * Genera el reporte semanal.
   */

  public static void main(String[] args) {
    InterfaceHeladerasRepository repo =
        (InterfaceHeladerasRepository) RepositoryLocator.get("heladerasRepository");
    GeneradorReporte generador = new GeneradorReporte(repo);
    generador.generarReporte();
  }
}
