package models.entities.formulario;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa un formulario. Tiene un listado de preguntas.
 */

@Getter
@Setter
@Entity
@Table(name = "formularios")
public class Formulario extends Persistente {

  @OneToMany
  @JoinColumn(name = "pregunta_id")
  private List<Pregunta> preguntas;
}
