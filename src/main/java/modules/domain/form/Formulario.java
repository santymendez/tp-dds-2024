package modules.domain.form;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un formulario. Tiene un listado de preguntas.
 */

@Getter
@Setter
public class Formulario {
  private List<Pregunta> preguntas;
}
