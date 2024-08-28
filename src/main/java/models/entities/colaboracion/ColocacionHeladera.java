package models.entities.colaboracion;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;

/**
 * Colaboracion para Colocar una Heladera.
 */

@Getter
@Setter
@Embeddable
public class ColocacionHeladera {
  @ManyToOne
  private Heladera heladera;
}
