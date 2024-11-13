package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el ingreso de datos de un formulario.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormularioInputdto {

  String nombre;
  String cantPreguntas;

  /**
   * Sirve para obtener un DTO a partir de un contexto.
   *
   * @param context Contexto de la aplicación.
   * @return DTO con los datos del formulario.
   */

  public static FormularioInputdto from(Context context) {
    return FormularioInputdto.builder()
        .nombre(context.formParam("nombreForm"))
        .cantPreguntas(context.formParam("cantPreguntas"))
        .build();
  }
}
