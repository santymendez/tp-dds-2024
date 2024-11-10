package dtos;

import lombok.Builder;
import lombok.Data;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;

/**
 * Representa el Input de la Solicitud de Apertura.
 */

@Data
@Builder
public class SolicitudAperturaOutputDto {
  private String uuid;
  private Long heladeraId;

  /**
   * Convierte un uso de tarjeta en un SolicitudAperturaOutputDto.
   *
   * @param usoTarjetaColaborador uso de tarjeta.
   * @return SolicitudAperturaOutputDto.
   */

  public static SolicitudAperturaOutputDto fromUsoTarjeta(
      UsoTarjetaColaborador usoTarjetaColaborador
  ) {
    return SolicitudAperturaOutputDto.builder()
        .uuid(usoTarjetaColaborador.getTarjetaColaborador().getCodigo())
        .heladeraId(usoTarjetaColaborador.getHeladera().getId())
        .build();
  }
}
