package models.entities.reporte;

import java.util.List;
import models.entities.heladera.Heladera;

/**
 * Clase que representa el reporte de una heladera.
 */

public class ReporteHeladera {
  private Heladera heladera;
  private Integer fallas;
  private Integer viandasColocadas;
  private Integer viandasRetiradas;
  private List<ViandasPorColaborador> viandasPorColaboradores;
}
