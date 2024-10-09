package services;

import dtos.DistribucionViandasDto;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.heladera.Heladera;

/**
 * Service para las distribuciones de viandas.
 */

public class DistribucionViandasService {

  /**
   * Crea una Distribucion de Viandas a partir de un DTO.
   *
   * @param distribucionDto el DTO con los datos de la distribucion.
   * @param heladeraOrigen heladera de origen de la distribucion.
   * @param heladeraDestino heladera destino de la distribucion.
   * @return la oferta creada.
   */

  public DistribucionViandas crear(DistribucionViandasDto distribucionDto,
                                   Heladera heladeraOrigen,
                                   Heladera heladeraDestino) {
    DistribucionViandas distribucionViandas = new DistribucionViandas();
    distribucionViandas.setMotivoDistribucion(distribucionDto.getMotivoDistribucion());
    distribucionViandas.setCantViandasDistribuidas(
        Integer.parseInt(distribucionDto.getCantViandasDistribuidas())
    );
    distribucionViandas.setHeladeraOrigen(heladeraOrigen);
    distribucionViandas.setHeladeraDestino(heladeraDestino);

    return distribucionViandas;
  }
}
