package dtos;

import io.javalin.http.Context;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;

/**
 * DTO para la creación de un modelo.
 */

@Data
@Builder
public class ModeloInputDto {
  private String nombre;
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Integer capacidadMaximaViandas;

  /**
   * Crea un ModeloInputDto a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return un ModeloInputDto.
   */

  public static ModeloInputDto fromContext(Context context) {
    return ModeloInputDto.builder()
        .nombre(context.formParam("nombre"))
        .temperaturaMinima(
            Float.valueOf(Objects.requireNonNull(context.formParam("temperaturaMin")))
        )
        .temperaturaMaxima(
            Float.valueOf(Objects.requireNonNull(context.formParam("temperaturaMax")))
        )
        .capacidadMaximaViandas(
            Integer.valueOf(Objects.requireNonNull(context.formParam("capacidadMaximaViandas")))
        )
        .build();
  }
}
