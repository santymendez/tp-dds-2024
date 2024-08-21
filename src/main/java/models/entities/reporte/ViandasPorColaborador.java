package models.entities.reporte;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que relaciona a un colaborador con las viandas
 * que dono a una heladera.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viandasPorColaborador")
public class ViandasPorColaborador {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private Colaborador colaborador;

  @Column(name = "cantidadViandas")
  private Integer viandas;

  public void agregarViandas(Integer cantViandas) {
    this.viandas += cantViandas;
  }

  public ViandasPorColaborador(Colaborador colaborador, Integer viandas) {
    this.colaborador = colaborador;
    this.viandas = viandas;
  }
}
