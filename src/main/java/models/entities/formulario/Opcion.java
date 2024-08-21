package models.entities.formulario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una opcion de las preguntas de formularios.
 */

@Getter
@Setter
@Entity
@Table(name = "opciones") // TODO es muy rariiii
public class Opcion {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "opcion")
  private String opcion;
}
