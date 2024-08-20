package models.entities.formulario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;

import javax.persistence.*;

/**
 * Representa una pregunta de un formulario.
 * Tiene la pregunta en sí misma, la opcionalidad, las opciones y el tipo de la respuesta.
 */

@Getter
@Setter
@Entity
@Table(name = "preguntas")
public class Pregunta extends EntidadPersistente {
  @Column(name = "pregunta")
  private String pregunta;

  @Column(name = "opcional")
  private Boolean esOpcional;

  @OneToMany
  @JoinColumn(name = "opcion_id")
  private List<Opcion> opciones;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoPregunta tipoDeSuRespuesta;
}
