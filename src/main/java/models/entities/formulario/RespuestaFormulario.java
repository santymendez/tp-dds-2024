package models.entities.formulario;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa las respuestas de los contribuidores a la totalidad de un formulario.
 * Tiene un nombre, la descripcion, las respuestas y el formulario al que responde.
 */

@Getter
@Setter
@Entity
@Table(name = "respuesta_formulario")
public class RespuestaFormulario {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "descripcion")
  private String descripcion;

  @Transient
  private List<Respuesta> respuestas;

  @Transient
  private Formulario formulario;

  /*public String respuestaNombre(){
    return respuestas.stream().findFirst(r -> r.getPregunta().getPregunta() == "Nombre")
    .getRespuestaTextoLibre();
  }
  */
}
