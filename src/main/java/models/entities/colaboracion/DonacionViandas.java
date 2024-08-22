package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.vianda.Vianda;

/**
 * Colaboracion para Donar Viandas.
 */

@Getter
@Setter
@Embeddable
public class DonacionViandas {
  @Transient // TODO hacer relacion
  private List<Vianda> viandas;
  @Column(name = "cantidadViandasDonadas")
  private Integer cantViandas;

  public DonacionViandas() {
    this.viandas = new ArrayList<>();
  }

}
