package dtos;

import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO para la creación de una heladera.
 */

@Data
@AllArgsConstructor
@Builder
public class HeladeraInputDto {
  private String nombre;
  private LocalDate fechaCreacion;

  /**
   * Crea un HeladeraInputDto a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return un HeladeraInputDto.
   */

  public static HeladeraInputDto fromContext(Context context) {
    return HeladeraInputDto.builder()
        .nombre(context.formParam("nombre"))
        .fechaCreacion(
            LocalDate.parse(Objects.requireNonNull(context.formParam("fechaCreacion")))
        )
        .build();
  }
}
