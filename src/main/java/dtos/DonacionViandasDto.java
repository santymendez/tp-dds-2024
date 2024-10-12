package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  DTO de la donacion de una vianda.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonacionViandasDto {
  private String cantViandas;
  private String calorias;
  private String peso;
  private String nombreComida;
  private String fechaVencimiento;

  /**
   * Crea una donacion de viandas a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return una donacion de viandas.
   */

  public static DonacionViandasDto fromContext(Context context) {
    return DonacionViandasDto.builder()
        .cantViandas(context.formParam("cantidad"))
        .peso(context.formParam("peso"))
        .fechaVencimiento(context.formParam("fechaVencimiento"))
        .calorias(context.formParam("calorias"))
        .nombreComida(context.formParam("nombreComida"))
        .build();
  }
}
