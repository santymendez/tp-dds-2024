package models.entities.formulario;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un formulario. Tiene un listado de preguntas.
 */

@Getter
@Setter
@Entity
@Table(name = "formularios")
public class Formulario {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  @JoinColumn(name = "pregunta_id")
  private List<Pregunta> preguntas;
}
