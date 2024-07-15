package models.entities.colaboracion;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;

/**
 * Clase la cual va a encargarse de la distribución de viandas como colaboración.
 */

@Setter
@Getter
public class DistribucionViandas {
  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private Integer cantViandasDistribuidas;
  private String motivoDistribucion;
}
