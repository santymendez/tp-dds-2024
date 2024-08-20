package models.entities.formulario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;

import javax.persistence.*;

/**
 * Representa un formulario. Tiene un listado de preguntas.
 */

@Getter
@Setter
@Entity
@Table(name = "formularios")
public class Formulario extends EntidadPersistente {
  @OneToMany
  @JoinColumn(name = "pregunta_id")
  private List<Pregunta> preguntas;
}
