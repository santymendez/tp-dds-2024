package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.UploadedFilesHelper;
import java.util.Objects;

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

  public static IncidenteDto fromContext(Context context) {
    String path = UploadedFilesHelper.getImageFromContext(context);

    return IncidenteDto.builder()
        .descripcion(context.formParam("descripcion"))
        .imagen(Objects.requireNonNullElse(path, ""))
        .build();
  }
}
