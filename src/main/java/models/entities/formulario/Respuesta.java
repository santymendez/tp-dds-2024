package models.entities.formulario;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;


/**
 * Representa las respuestas a las preguntas de un formulario.
 */

@Getter
@Setter
@Entity
@Table(name = "respuestas")
public class Respuesta extends Persistente {

  @OneToOne //Es One to One
  @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
  private Pregunta pregunta;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoPregunta tipoRespuesta;

  //Las respuestas segun el tipo
  @Column(name = "respuestaTextoLibre", columnDefinition = "TEXT")
  private String respuestaTextoLibre;

  @OneToMany
  @JoinColumn(name = "opcion_id")
  private List<Opcion> opciones;

  @OneToOne //?????
  @JoinColumn //?¿¿¿¿???
  private Opcion respuestaSingleChoice;
}
