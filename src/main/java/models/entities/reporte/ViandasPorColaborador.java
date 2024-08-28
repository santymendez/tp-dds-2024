package models.entities.reporte;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que relaciona a un colaborador con las viandas
 * que dono a una heladera.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viandas_por_colaboradores")
public class ViandasPorColaborador extends Persistente {

  @OneToOne
  private Colaborador colaborador;

  @Column(name = "cantidadViandas")
  private Integer viandas;

  public void agregarViandas(Integer cantViandas) {
    this.viandas += cantViandas;
  }

}
