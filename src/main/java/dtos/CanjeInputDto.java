package dtos;

import io.javalin.http.Context;
import lombok.Data;

/**
 * Representa un objeto de transferencia de datos para el canje de puntos.
 */

@Data
public class CanjeInputDto {
  private String idOferta;
  private String idColaborador;

  /**
   * Devuelve el id de la oferta.
   *
   * @param context el contexto de la aplicación.
   * @return el id de la oferta.
   */

  public static CanjeInputDto fromContext(Context context) {
    CanjeInputDto canjeInputDto = new CanjeInputDto();
    canjeInputDto.setIdOferta(context.formParam("idOferta"));
    canjeInputDto.setIdColaborador(context.formParam("idColaborador"));
    return canjeInputDto;
  }
}
