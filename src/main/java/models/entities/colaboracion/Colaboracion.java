package models.entities.colaboracion;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "colaboraciones")
public class Colaboracion extends Persistente {
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false)
  private TipoColaboracion tipoColaboracion;

  @Column(name = "fechaColaboracion", columnDefinition = "DATE")
  private LocalDate fechaColaboracion;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @Embedded
  private DonacionDinero donacionDinero;

  @Embedded
  private DonacionViandas donacionViandas;

  @Embedded
  private DistribucionViandas distribucionViandas;

  @Embedded
  private DistribucionTarjetas distribucionTarjetas;

  @Embedded
  private HacerseCargoHeladera hacerseCargoHeladera;

  @Embedded
  private RealizacionOferta ofertaRealizada;

  /**
   * Constructor de colaboracion.
   *
   * @param colaboracion colaboracion a copiar.
   */

  public Colaboracion(Colaboracion colaboracion) {
    this.tipoColaboracion = colaboracion.getTipoColaboracion();
    this.fechaColaboracion = colaboracion.getFechaColaboracion();
    this.colaborador = colaboracion.getColaborador();
    this.donacionViandas = colaboracion.getDonacionViandas();
    this.donacionDinero = colaboracion.getDonacionDinero();
    this.distribucionViandas = colaboracion.getDistribucionViandas();
    this.distribucionTarjetas = colaboracion.getDistribucionTarjetas();
    this.hacerseCargoHeladera = colaboracion.getHacerseCargoHeladera();
    this.ofertaRealizada = colaboracion.getOfertaRealizada();
  }

  @PrePersist
  protected void onInsert() {
    if (this.fechaColaboracion == null) {
      this.fechaColaboracion = LocalDate.now();
    }
  }

  //=============== Auxiliar Methods ===========================

  /**
   * Indica si la colaboracion es parcial
   * Una colaboracion es parcial cuando se completa despues de hacer algo
   * fisico (dejar una vianda en la heladera, entregar la tarjeta al vulnerable, etc).
   *
   * @return true si la colaboracion es parcial, false en caso contrario.
   */

  public Boolean noEsParcial() {
    return donacionViandas == null
        && distribucionTarjetas == null
        && distribucionViandas == null;
  }
}
