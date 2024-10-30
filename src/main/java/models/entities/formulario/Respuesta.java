package models.entities.formulario;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

  @ManyToOne
  @JoinColumn(name = "pregunta_id", referencedColumnName = "id", nullable = false)
  private Pregunta pregunta;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false)
  private TipoPregunta tipoRespuesta;

  //Las respuestas segun el tipo
  @Column(name = "respuestaTextoLibre", columnDefinition = "TEXT")
  private String respuestaTextoLibre;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "respuestas_por_opciones",
      joinColumns = @JoinColumn(name = "respuesta_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id", referencedColumnName = "id"))
  private List<Opcion> opciones;

  @ManyToOne
  @JoinColumn(name = "opcion_id", referencedColumnName = "id")
  private Opcion respuestaSingleChoice;
}
