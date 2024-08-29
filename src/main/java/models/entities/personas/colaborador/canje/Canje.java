package models.entities.personas.colaborador.canje;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que relaciona un colaborador la oferta canjeada.
 */

@Entity
@Table(name = "canjes")
public class Canje extends Persistente {

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "oferta_id", referencedColumnName = "id")
  private Oferta oferta;
}
