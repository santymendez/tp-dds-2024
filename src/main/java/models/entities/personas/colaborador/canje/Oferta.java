package models.entities.personas.colaborador.canje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa las ofertas de las empresas asociadas.
 */

@Getter
@Setter
@Entity
@Table(name = "ofertas")
public class Oferta extends Persistente {
  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "puntosNecesarios", nullable = false)
  private Float puntosNecesarios;

  @Column(name = "imagen")
  private String imagenIlustrativa;

  @Column(name = "descripcion", nullable = false)
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "ofertante_id", referencedColumnName = "id", nullable = false)
  private Colaborador ofertante;
}
