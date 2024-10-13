package dtos;

import io.javalin.http.Context;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.helpers.UploadedFilesHelper;

/**
 * Clase DTO para los incidentes.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteDto {
  private String descripcion;
  private String imagen;

  /**
   * Sirve para obtener un objeto IncidenteDto a partir de un objeto Context.
   *
   * @param context Objeto Context.
   * @return Objeto IncidenteDto.
   */

  public static IncidenteDto fromContext(Context context) {
    String path = UploadedFilesHelper.getImageFromContext(context);

    return IncidenteDto.builder()
        .descripcion(context.formParam("descripcion"))
        .imagen(Objects.requireNonNullElse(path, ""))
        .build();
  }
}
