package models.entities.formulario;

import java.util.List;

import ch.qos.logback.core.model.Model;
import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Representa las respuestas de los contribuidores a la totalidad de un formulario.
 * Tiene un nombre, la descripcion, las respuestas y el formulario al que responde.
 */

@Getter
@Setter
@Entity
@Table(name = "rtaFormulario")
public class RespuestaFormulario extends EntidadPersistente {
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
