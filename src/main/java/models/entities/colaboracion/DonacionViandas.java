package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.vianda.Vianda;

/**
 * Colaboracion para Donar Viandas.
 */

@Getter
@Setter
public class DonacionViandas {
  private List<Vianda> viandas;
  private Integer cantViandas;

  public DonacionViandas() {
    this.viandas = new ArrayList<>();
  }

}
