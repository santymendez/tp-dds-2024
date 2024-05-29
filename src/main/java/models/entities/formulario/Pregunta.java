package models.entities.formulario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una pregunta de un formulario.
 * Tiene la pregunta en sí misma, la opcionalidad, las opciones y el tipo de la respuesta.
 */

@Getter
@Setter
public class Pregunta {
  private String pregunta;
  private Boolean esOpcional;
  private List<Opcion> opciones;
  private TipoPregunta tipoDeSuRespuesta;
}
