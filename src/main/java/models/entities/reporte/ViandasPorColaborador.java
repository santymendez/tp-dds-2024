package models.entities.reporte;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que relaciona a un colaborador con las viandas
 * que dono a una heladera.
 */

@Getter
@AllArgsConstructor
public class ViandasPorColaborador {
  private Colaborador colaborador;
  private Integer viandas;

  public void agregarViandas(Integer cantViandas) {
    this.viandas += cantViandas;
  }
}
