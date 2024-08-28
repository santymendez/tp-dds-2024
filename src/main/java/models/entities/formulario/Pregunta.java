package models.entities.formulario;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa una pregunta de un formulario.
 * Tiene la pregunta en sí misma, la opcionalidad, las opciones y el tipo de la respuesta.
 */

@Getter
@Setter
@Entity
@Table(name = "preguntas")
public class Pregunta extends Persistente {

  @Column(name = "pregunta")
  private String pregunta;

  @Column(name = "opcional")
  private Boolean esOpcional;

  @OneToMany
  @JoinColumn(name = "opcion_id", referencedColumnName = "id")
  private List<Opcion> opciones;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoPregunta tipoDeSuRespuesta;
}
