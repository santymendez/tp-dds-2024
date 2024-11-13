package services;

import dtos.PreguntaInputdto;
import java.util.List;
import java.util.stream.Collectors;
import models.entities.formulario.Opcion;
import models.entities.formulario.Pregunta;
import models.entities.formulario.TipoPregunta;

/**
 * Servicio de preguntas.
 */

public class PreguntasService {

  /**
   * Crea una pregunta a partir de un DTO.
   *
   * @param preguntaInputdto DTO con los datos de la pregunta.
   * @return Pregunta creada.
   */

  public Pregunta crearCon(PreguntaInputdto preguntaInputdto) {

    List<Opcion> opciones = preguntaInputdto.getOpciones()
        .stream().map(opcionStr -> {
          Opcion opcion = new Opcion();
          opcion.setOpcion(opcionStr);
          return opcion;
        }).collect(Collectors.toList());

    TipoPregunta tipo = switch (preguntaInputdto.getTipoPregunta()) {
      case "texto_libre" -> TipoPregunta.TEXTO_LIBRE;
      case "single_choice" -> TipoPregunta.SINGLE_CHOICE;
      case "multiple_choice" -> TipoPregunta.MULTIPLE_CHOICE;
      default -> null;
    };

    Pregunta pregunta = new Pregunta(
        preguntaInputdto.getNombre(),
        "TRUE".equals(preguntaInputdto.getEsOpcional()),
        opciones,
        tipo
    );

    return pregunta;
  }
}
