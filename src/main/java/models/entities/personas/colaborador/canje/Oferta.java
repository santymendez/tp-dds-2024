package models.entities.personas.colaborador.canje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa las ofertas de las empresas asociadas.
 */

@Getter
@Setter
@Entity
@Table(name = "ofertas")
public class Oferta {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "puntosNecesarios")
  private Float puntosNecesarios;

  @Column(name = "imagen")
  private String imagenIlustrativa;

  @ManyToOne
  @Column(name = "colaborador_id")
  private Colaborador ofertante;
}
