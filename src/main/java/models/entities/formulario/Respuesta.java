package models.entities.formulario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;

import javax.persistence.*;

/**
 * Representa las respuestas a las preguntas de un formulario.
 */

@Getter
@Setter
@Entity
@Table(name = "respuestas")
public class Respuesta extends EntidadPersistente {
  @Transient //Es One to One
  private Pregunta pregunta;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoPregunta tipoRespuesta;

  //Las respuestas segun el tipo
  @Transient
  private String respuestaTextoLibre;

  @OneToMany
  @JoinColumn(name = "opcion_id")
  private List<Opcion> opciones;

  @Transient // ManyToOne?
  private Opcion respuestaSingleChoice;
}
