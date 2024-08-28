package models.entities.formulario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa una opcion de las preguntas de formularios.
 */

@Getter
@Setter
@Entity
@Table(name = "opciones") // TODO es muy rariiii
public class Opcion extends Persistente {

  @Column(name = "opcion", nullable = false)
  private String opcion;
}
