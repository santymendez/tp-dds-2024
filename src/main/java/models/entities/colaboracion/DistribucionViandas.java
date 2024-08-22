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
  // TODO medio rariii, por que aca tengo 2 y en el otro 1 heladera mas
  // no se como deberia referenciar a las heladeras
  @ManyToOne
  private Heladera heladeraOrigen;

  @ManyToOne
  private Heladera heladeraDestino;

  @Column(name = "cantidadViandasDistribuidas")
  private Integer cantViandasDistribuidas;

  @Column(name = "motivoDistribucion")
  private String motivoDistribucion;
}
