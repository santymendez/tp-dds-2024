package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
  @OneToMany(cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "vianda_id", referencedColumnName = "id")
  private List<Vianda> viandas;

  @Column(name = "cantidadViandasDonadas")
  private Integer cantViandas;

  public DonacionViandas() {
    this.viandas = new ArrayList<>();
  }

}
