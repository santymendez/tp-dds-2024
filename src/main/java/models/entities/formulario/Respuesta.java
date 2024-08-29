package models.entities.formulario;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

  @OneToOne
  @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
  private Pregunta pregunta;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoPregunta tipoRespuesta;

  //Las respuestas segun el tipo
  @Column(name = "respuestaTextoLibre", columnDefinition = "TEXT")
  private String respuestaTextoLibre;

  @OneToMany(cascade = {CascadeType.PERSIST}, fetch =  FetchType.EAGER)
  @JoinColumn(name = "opcion_id")
  private List<Opcion> opciones;

  @OneToOne
  @JoinColumn(name = "opcion_id", referencedColumnName = "id")
  private Opcion respuestaSingleChoice;
}
