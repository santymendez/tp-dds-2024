package dtos;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import utils.helpers.ContextHelper;

/**
 * DTO para la suscripción de heladeras.
 */

@Data
public class SuscripcionInputDto {
  private String viandasFaltantes;
  private String viandasRestantes;
  private List<String> tiposSuscripcion;

  /**
   * Constructor de SuscripcionInputDto.
   *
   * @param context Contexto de la aplicación.
   * @return SuscripcionInputDto.
   */

  public static SuscripcionInputDto fromContext(Context context) {
    SuscripcionInputDto suscripcionInputDto = new SuscripcionInputDto();
    suscripcionInputDto.tiposSuscripcion = new ArrayList<>();

    if (!ContextHelper.isEmpty(context, "faltan_n_viandas_check")) {
      suscripcionInputDto.viandasFaltantes = context.formParam("faltan_n_viandas");
      suscripcionInputDto.tiposSuscripcion.add(context.formParam("faltan_n_viandas_check"));
    }

    if (!ContextHelper.isEmpty(context, "quedan_n_viandas_check")) {
      suscripcionInputDto.viandasRestantes = context.formParam("quedan_n_viandas");
      suscripcionInputDto.tiposSuscripcion.add(context.formParam("quedan_n_viandas_check"));
    }

    if (!ContextHelper.isEmpty(context, "desperfecto")) {
      suscripcionInputDto.tiposSuscripcion.add(context.formParam("desperfecto"));
    }

    return suscripcionInputDto;
  }
}
