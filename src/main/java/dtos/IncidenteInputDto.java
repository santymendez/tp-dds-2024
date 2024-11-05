package dtos;

import io.javalin.http.Context;
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
public class IncidenteInputDto {
  private String descripcion;
  private String imagen;

  /**
   * Sirve para obtener un objeto IncidenteDto a partir de un objeto Context.
   *
   * @param context Objeto Context.
   * @return Objeto IncidenteDto.
   */

  public static IncidenteInputDto fromContext(Context context) {
    return IncidenteInputDto.builder()
        .descripcion(context.formParam("descripcion"))
        .imagen(UploadedFilesHelper.getImageFromContext(context))
        .build();
  }
}
