package models.entities.formulario;

import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa una opcion de las preguntas de formularios.
 */

@Getter
@Setter
@Entity
@Table(name = "opciones") // TODO es muy rariiii
public class Opcion extends EntidadPersistente {

  @Column(name = "opcion")
  private String opcion;
}
