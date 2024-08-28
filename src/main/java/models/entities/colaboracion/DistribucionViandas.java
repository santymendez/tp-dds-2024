package models.entities.colaboracion;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;

/**
 * Clase la cual va a encargarse de la distribución de viandas como colaboración.
 */

@Setter
@Getter
@Embeddable
public class DistribucionViandas {
  @ManyToOne
  private Heladera heladeraOrigen;

  @ManyToOne
  private Heladera heladeraDestino;

  @Column(name = "cantidadViandasDistribuidas")
  private Integer cantViandasDistribuidas;

  @Column(name = "motivoDistribucion")
  private String motivoDistribucion;
}
