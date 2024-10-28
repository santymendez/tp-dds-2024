package dtos;

import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.Objects;
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
public class DonacionViandasInputDto {
  private Integer cantViandas;
  private Integer calorias;
  private Float peso;
  private String nombreComida;
  private LocalDate fechaVencimiento;

  /**
   * Crea una donacion de viandas a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return una donacion de viandas.
   */

  public static DonacionViandasInputDto fromContext(Context context) {
    return DonacionViandasInputDto.builder()
        .cantViandas(
            Integer.valueOf(Objects.requireNonNull(context.formParam("cantidad")))
        )
        .peso(Float.valueOf(Objects.requireNonNull(context.formParam("peso"))))
        .fechaVencimiento(
            LocalDate.parse(Objects.requireNonNull(context.formParam("fechaVencimiento")))
        )
        .calorias(Integer.valueOf(Objects.requireNonNull(context.formParam("calorias"))))
        .nombreComida(context.formParam("nombreComida"))
        .build();
  }
}
