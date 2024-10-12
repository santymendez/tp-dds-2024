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

  /**
   * Crea una donacion de dinero a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return una donacion de dinero.
   */

  public static DonacionDineroDto fromContext(Context context) {
    DonacionDineroDto nuevaDonacion = new DonacionDineroDto();
    nuevaDonacion.setMontoDonado(context.formParam("montoDonado"));
    nuevaDonacion.setFrecuencia(context.formParam("frecuenciaDonacion"));
    return nuevaDonacion;
  }
}
