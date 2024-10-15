package services;

import models.entities.heladera.Heladera;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;

/**
 * Servicio para la creacion de reportes de heladeras.
 */

public class ReportesHeladerasService {
  private final GenericRepository genericRepository;

  public ReportesHeladerasService(GenericRepository genericRepository) {
    this.genericRepository = genericRepository;
  }

  public void crear(Heladera heladera) {
    ReporteHeladera nuevoReporte = new ReporteHeladera(heladera);
    this.genericRepository.guardar(nuevoReporte);
  }
}
