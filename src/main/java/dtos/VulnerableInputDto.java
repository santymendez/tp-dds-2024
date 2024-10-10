package dtos;

import io.javalin.http.Context;
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
  String nombre;
  String fechaNacimiento;
  String tipoDocumento;
  String numeroDocumento;
  String cantMenores;
  String tarjeta;

  /**
   * Instancia el VulnerableInputDTO.
   *
   * @param context el contexto con los valores recibidos del formulario.
   * @return Un vulnerableInputDTO.
   */

  public static VulnerableInputDto fromContext(Context context) {
    return VulnerableInputDto.builder()
        .nombre(context.formParam("nombre"))
        .fechaNacimiento(context.formParam("fechaNacimiento"))
        .tipoDocumento(context.formParam("tipoDocumento"))
        .numeroDocumento(context.formParam("numeroDocumento"))
        .cantMenores(context.formParam("cantMenores"))
        .tarjeta(context.formParam("tarjeta"))
        .build();
  }

  public static VulnerableInputDto fromContext (Context context, int i) {
    String plantilla = "menores[" + i + "]";

    return VulnerableInputDto.builder()
        .nombre(context.formParam(plantilla + "[nombre]"))
        .fechaNacimiento(context.formParam(plantilla + "[fechaNacimiento]"))
        .tipoDocumento(context.formParam(plantilla + "[tipoDocumento]"))
        .numeroDocumento(context.formParam(plantilla + "[numeroDocumento]"))
        .cantMenores("0")
        .build();
  }
}
