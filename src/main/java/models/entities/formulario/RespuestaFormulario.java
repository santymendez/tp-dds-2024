package models.entities.formulario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa las respuestas de los contribuidores a la totalidad de un formulario.
 * Tiene un nombre, la descripcion, las respuestas y el formulario al que responde.
 */

@Getter
@Setter
public class RespuestaFormulario {
  private String nombre;
  private String descripcion;
  private List<Respuesta> respuestas;
  private Formulario formulario;

  /*public String respuestaNombre(){
    return respuestas.stream().findFirst(r -> r.getPregunta().getPregunta() == "Nombre")
    .getRespuestaTextoLibre();
  }
  */
}
