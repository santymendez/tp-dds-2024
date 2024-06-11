package models.entities.reporte;

import java.util.List;
import models.repositories.InterfaceIncidentesRepository;

/**
 * Clase que representa los reportes historicos.
 */

public class ReporteGeneral {

  public ReporteGeneral(InterfaceIncidentesRepository incidentesRepository) {}

  private List<ReporteHeladera> reporteHeladeras;
}
