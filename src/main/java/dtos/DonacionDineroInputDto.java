package dtos;

import io.javalin.http.Context;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para donacion de dinero.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDineroInputDto {
  private Integer montoDonado;
  private String frecuencia;

  /**
   * Crea una donacion de dinero a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return una donacion de dinero.
   */

  public static DonacionDineroInputDto fromContext(Context context) {
    DonacionDineroInputDto nuevaDonacion = new DonacionDineroInputDto();
    nuevaDonacion.setMontoDonado(
        Integer.valueOf(Objects.requireNonNull(context.formParam("montoDonado")))
    );
    nuevaDonacion.setFrecuencia(context.formParam("frecuenciaDonacion"));
    return nuevaDonacion;
  }
}
