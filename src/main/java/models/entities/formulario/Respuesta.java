package models.entities.formulario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa las respuestas a las preguntas de un formulario.
 */

@Getter
@Setter
public class Respuesta {
  private Pregunta pregunta;
  private TipoPregunta tipoRespuesta;

  //Las respuestas segun el tipo
  private String respuestaTextoLibre;
  private List<Opcion> opciones;
  private Opcion respuestaSingleChoice;
}
