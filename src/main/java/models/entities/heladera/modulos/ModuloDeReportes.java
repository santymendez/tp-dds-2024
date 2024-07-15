package models.entities.heladera.modulos;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.reporte.ReporteHeladera;

/**
 * Representa al modulo encargado de la actualizacion
 * de los reportes correspondientes a la heladera.
 */

@Getter
@Setter
public class ModuloDeReportes {
  private ReporteHeladera reporteHeladera;

  public ModuloDeReportes(Heladera heladera) {
    this.reporteHeladera = new ReporteHeladera(heladera);
  }

  public void reportarFalla() {
    reporteHeladera.ocurrioUnaFalla();
  }
}
