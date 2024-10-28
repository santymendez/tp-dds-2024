package dtos;

import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el ingreso de datos de un vulnerable.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VulnerableInputDto {
  private String nombre;
  private LocalDate fechaNacimiento;
  private String tipoDocumento;
  private Integer numeroDocumento;
  private Integer cantMenores;
  private String tarjeta;

  /**
   * Instancia el VulnerableInputDTO.
   *
   * @param context el contexto con los valores recibidos del formulario.
   * @return Un vulnerableInputDTO.
   */

  public static VulnerableInputDto fromContext(Context context) {
    return VulnerableInputDto.builder()
        .nombre(context.formParam("nombre"))
        .fechaNacimiento(
            LocalDate.parse(Objects.requireNonNull(context.formParam("fechaNacimiento")))
        )
        .tipoDocumento(context.formParam("tipoDocumento"))
        .numeroDocumento(
            Integer.valueOf(Objects.requireNonNull(context.formParam("numeroDocumento")))
        )
        .cantMenores(
            Integer.valueOf(Objects.requireNonNull(context.formParam("cantMenores")))
        )
        .tarjeta(context.formParam("tarjeta"))
        .build();
  }

  /**
   * Instancia el VulnerableInputDTO.
   *
   * @param context el contexto con los valores recibidos del formulario.
   * @param i el índice del menor.
   * @return Un vulnerableInputDTO.
   */

  public static VulnerableInputDto fromContext(Context context, int i) {
    String plantilla = "menores[" + i + "]";

    return VulnerableInputDto.builder()
        .nombre(context.formParam(plantilla + "[nombre]"))
        .fechaNacimiento(LocalDate.parse(
            Objects.requireNonNull(context.formParam(plantilla + "[fechaNacimiento]"))
            )
        )
        .tipoDocumento(context.formParam(plantilla + "[tipoDocumento]"))
        .numeroDocumento(Integer.valueOf(
            Objects.requireNonNull(context.formParam(plantilla + "[numeroDocumento]")))
        )
        .cantMenores(0)
        .build();
  }
}
