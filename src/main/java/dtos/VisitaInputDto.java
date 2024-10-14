package dtos;

import io.javalin.http.Context;
import lombok.Builder;
import lombok.Data;

/**
 * Representa el Input de una Visita Tecnica.
 */

@Data
@Builder
public class VisitaInputDto {
  private String fechaVisita;
  private String trabajoRealizado;
  private String fotoVisita;
  private String incidenteSolucionado;

  public static VisitaInputDto fromContext(Context context) {
    return VisitaInputDto.builder()
        .fechaVisita(context.formParam("fechaVisita"))
        .trabajoRealizado(context.formParam("trabajoRealizado"))
        .fotoVisita(context.formParam("fotoVisita"))
        .incidenteSolucionado(context.formParam("incidenteSolucionado"))
        .build();
  }
}
