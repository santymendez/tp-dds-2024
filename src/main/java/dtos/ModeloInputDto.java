package dtos;

import io.javalin.http.Context;
import lombok.Builder;
import lombok.Data;

/**
 * DTO para la creación de un modelo.
 */

@Data
@Builder
public class ModeloInputDto {
  private String nombre;
  private String temperaturaMinima;
  private String temperaturaMaxima;
  private String capacidadMaximaViandas;

  /**
   * Crea un ModeloInputDto a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return un ModeloInputDto.
   */

  public static ModeloInputDto fromContext(Context context) {
    return ModeloInputDto.builder()
        .nombre(context.formParam("nombre"))
        .temperaturaMinima(context.formParam("temperaturaMin"))
        .temperaturaMaxima(context.formParam("temperaturaMax"))
        .capacidadMaximaViandas(context.formParam("capacidadMaximaViandas"))
        .build();
  }
}
