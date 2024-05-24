package models.entities.formulario;

import java.util.HashSet;
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
  private HashSet<Opcion> opciones;
  private Opcion respuestaSingleChoice;
}
