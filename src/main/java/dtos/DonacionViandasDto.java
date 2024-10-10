package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  DTO de la donacion de una vianda.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonacionViandasDto {
  private String tipoVianda;
  private String cantViandas;

  /**
   * Crea una donacion de viandas a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return una donacion de viandas.
   */

  public static DonacionViandasDto fromContext(Context context) {
    DonacionViandasDto donacionViandasDto = new DonacionViandasDto();
    donacionViandasDto.setCantViandas(context.pathParam("cantViandas"));
    donacionViandasDto.setTipoVianda(context.pathParam("descripcion"));
    return donacionViandasDto;
  }
}
