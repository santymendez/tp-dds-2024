package dtos;

import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;
import utils.helpers.UploadedFilesHelper;

/**
 * Representa el Input de una Visita Tecnica.
 */

@Data
@Builder
public class VisitaInputDto {
  private LocalDate fechaVisita;
  private String trabajoRealizado;
  private String fotoVisita;
  private Boolean incidenteSolucionado;

  /**
   * Crea un objeto VisitaInputDto a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return un objeto VisitaInputDto.
   */

  public static VisitaInputDto fromContext(Context context) {
    return VisitaInputDto.builder()
        .fechaVisita(LocalDate.parse(Objects.requireNonNull(context.formParam("fechaVisita"))))
        .trabajoRealizado(context.formParam("trabajoRealizado"))
        .fotoVisita(UploadedFilesHelper.getImageFromContext(context))
        .incidenteSolucionado(Boolean.valueOf(context.formParam("incidenteSolucionado")))
        .build();
  }
}
