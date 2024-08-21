package models.entities.formulario;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;


/**
 * Representa las respuestas a las preguntas de un formulario.
 */

@Getter
@Setter
@Entity
@Table(name = "respuestas")
public class Respuesta {
  @Id
  @GeneratedValue
  private Long id;

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
