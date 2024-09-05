package models.entities.heladera.vianda;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa una vianda que incluye una comida, fecha de donación, heladera, calorías,
 * peso y estado de entrega.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "viandas")
public class Vianda extends Persistente {

  @Embedded
  private Comida comida;

  @Column(name = "fechaDonacion")
  private LocalDate fechaDonacion;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", nullable = false)
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "heladera_id", nullable = false)
  private Heladera heladera;

  @Column(name = "calorias", nullable = false)
  private Integer calorias;

  @Column(name = "peso", nullable = false)
  private Float peso;

  @Setter
  @Column(name = "estaEntregada")
  private Boolean entregada;

  @PrePersist
  protected void onInsert() {
    if (this.fechaDonacion == null) {
      this.fechaDonacion = LocalDate.now();
    }
  }

}