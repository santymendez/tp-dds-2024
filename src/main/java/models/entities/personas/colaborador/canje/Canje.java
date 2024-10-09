package models.entities.personas.colaborador.canje;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que relaciona un colaborador la oferta canjeada.
 */

@Entity
@Table(name = "canjes")
@AllArgsConstructor
@NoArgsConstructor
public class Canje extends Persistente {

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "oferta_id", referencedColumnName = "id", nullable = false)
  private Oferta oferta;
}
