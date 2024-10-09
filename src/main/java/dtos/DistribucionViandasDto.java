package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de distribucion de viandas.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistribucionViandasDto {
  private String heladeraOrigen;
  private String heladeraDestino;
  private String cantViandasDistribuidas;
  private String motivoDistribucion;

  /**
   * Crea un dto de distribucion de viandas a partir del contexto.
   *
   * @param context Contexto.
   * @return dto generado.
   */

  public static DistribucionViandasDto fromContext(Context context) {
    DistribucionViandasDto distribucionViandasDto = new DistribucionViandasDto();
    distribucionViandasDto.setMotivoDistribucion(context.formParam("motivo"));
    distribucionViandasDto.setCantViandasDistribuidas(context.formParam("cantidad"));
    distribucionViandasDto.setHeladeraDestino(context.formParam("heladeraDestino"));
    distribucionViandasDto.setHeladeraOrigen(context.formParam("heladeraOrigen"));
    return distribucionViandasDto;
  }
}
