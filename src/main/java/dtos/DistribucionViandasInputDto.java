package dtos;

import io.javalin.http.Context;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de distribucion de viandas.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistribucionViandasInputDto {
  private String heladeraOrigen;
  private String heladeraDestino;
  private Integer cantViandasDistribuidas;
  private String motivoDistribucion;

  /**
   * Crea un dto de distribucion de viandas a partir del contexto.
   *
   * @param context Contexto.
   * @return dto generado.
   */

  public static DistribucionViandasInputDto fromContext(Context context) {
    DistribucionViandasInputDto distribucionViandasInputDto = new DistribucionViandasInputDto();
    distribucionViandasInputDto.setMotivoDistribucion(context.formParam("motivo"));
    distribucionViandasInputDto.setCantViandasDistribuidas(
        Integer.valueOf(Objects.requireNonNull(context.formParam("cantidad")))
    );
    distribucionViandasInputDto.setHeladeraDestino(context.formParam("heladeraDestino"));
    distribucionViandasInputDto.setHeladeraOrigen(context.formParam("heladeraOrigen"));
    return distribucionViandasInputDto;
  }
}
