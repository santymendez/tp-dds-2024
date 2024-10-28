package dtos;

import io.javalin.http.Context;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.helpers.UploadedFilesHelper;

/**
 * Clase para el output de las ofertas.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfertaInputDto {
  private String nombre;
  private Float puntosNecesarios;
  private String imagenIlustrativa;
  private String descripcion;

  /**
   * Metodo que genera una oferta dto a partir del contexto.
   *
   * @param context Contexto.
   * @return OfertaInputDto generado.
   */

  public static OfertaInputDto fromContext(Context context) {
    String path = UploadedFilesHelper.getImageFromContext(context);

    if (path == null) {
      path = "/static-imgs/logo.png";
    }

    return OfertaInputDto.builder()
        .nombre(context.formParam("nombre"))
        .descripcion(context.formParam("descripcion"))
        .puntosNecesarios(
            Float.valueOf(Objects.requireNonNull(context.formParam("puntosNecesarios")))
        )
        .imagenIlustrativa(path)
        .build();
  }
}
