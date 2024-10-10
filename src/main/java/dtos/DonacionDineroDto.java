package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para donacion de dinero.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDineroDto {
  private String montoDonado;
  private String frecuencia;

  public static DonacionDineroDto fromContext(Context context) {
    DonacionDineroDto nuevaDonacion = new DonacionDineroDto();
    context.formParam("montoDonado");
    context.formParam("frecuenciaDonacion");
    return nuevaDonacion;
  }
}
