package dtos;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el ingreso de datos de una pregunta.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreguntaInputdto {
  String nombre;
  String esObligatoria;
  String tipoPregunta;
  List<String> opciones;

  /**
   * Sirve para obtener un DTO a partir de un contexto.
   *
   * @param context Contexto de la aplicación.
   * @param index   Índice de la pregunta.
   * @return DTO con los datos de la pregunta.
   */

  public static PreguntaInputdto from(Context context, int index) {
    String tipoPregunta = context.formParam("tipoPregunta-" + index);
    List<String> opciones = new ArrayList<>();

    if ("MULTIPLE_CHOICE".equals(tipoPregunta) || "SINGLE_CHOICE".equals(tipoPregunta)) {
      int cantOpciones = Integer.parseInt(
          Objects.requireNonNull(context.formParam("cantOpciones-" + index)));
      opciones = obtenerOpciones(context, index, cantOpciones);
    }

    return PreguntaInputdto.builder()
        .nombre(context.formParam("preguntaNombre-" + index))
        .esObligatoria((context.formParam("preguntaObligatoria-" + index) != null
            && !context.formParam("preguntaObligatoria-" + index).isBlank())
            ? context.formParam("preguntaObligatoria-" + index) : "FALSE")
        .tipoPregunta(tipoPregunta)
        .opciones(opciones)
        .build();
  }


  /**
   * Obtiene las opciones de una pregunta.
   *
   * @param context      Contexto de la aplicación.
   * @param index        Índice de la pregunta.
   * @param cantOpciones Cantidad de opciones.
   * @return Lista con las opciones.
   */

  public static List<String> obtenerOpciones(Context context, int index, int cantOpciones) {
    List<String> opciones = new ArrayList<>();
    for (int i = 0; i < cantOpciones; i++) {
      opciones.add(context.formParam("opcion-" + index + "-" + i));
    }
    return opciones;
  }
}
