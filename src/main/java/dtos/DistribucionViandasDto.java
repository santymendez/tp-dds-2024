package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO de distribucion de viandas.
 */

@Data
@AllArgsConstructor
public class DistribucionViandasDto {
  private String heladeraOrigen;

  private String heladeraDestino;

  private String cantViandasDistribuidas;

  private String motivoDistribucion;
}
