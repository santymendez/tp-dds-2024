package models.entities.personas.colaborador.canje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que relaciona un colaborador la oferta canjeada.
 */

@Entity
@Table(name = "canjes")
public class Canje {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "oferta_id")
  private Oferta oferta;
}
