package models.entities.formulario;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;


/**
 * Representa las respuestas a las preguntas de un formulario.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "respuestas")
public class Respuesta extends Persistente {

  @ManyToOne
  @JoinColumn(name = "pregunta_id", referencedColumnName = "id", nullable = false)
  private Pregunta pregunta;

  @Column(name = "respuesta", columnDefinition = "TEXT")
  private String respuesta;

  @ManyToMany
  @JoinTable(name = "respuestas_por_opciones",
      joinColumns = @JoinColumn(name = "respuesta_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id", referencedColumnName = "id"))
  private List<Opcion> opciones;

  public void agregarOpcion(Opcion opcion) {
    this.opciones.add(opcion);
  }
}
