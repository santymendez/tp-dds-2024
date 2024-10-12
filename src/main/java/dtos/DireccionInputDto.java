package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.ContextHelper;

/**
 * Representa el input de una direccion.
 */

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionInputDto {
  private String longitud;
  private String latitud;

  //Barrio
  private String barrio;
  private String calle;
  private String numero;

  //Ciudad
  private String ciudad;

  //Provincia
  private String provincia;
  private String nombreUbicacion;

  /**
   * Crea un DireccionInputDto a partir de un contexto.
   *
   * @param context el contexto de la aplicación.
   * @return un DireccionInputDto.
   */

  public static DireccionInputDto fromContext(Context context) {
    if (ContextHelper.areEmpty(context,
        "barrio",
        "calle",
        "numero",
        "latitud",
        "longitud",
        "ciudad",
        "provincia",
        "nombreUbicacion")) {
      return null;
    }

    DireccionInputDto direccionInputDto = new DireccionInputDto();

    if (!ContextHelper.isEmpty(context, "longitud")) {
      direccionInputDto.setLongitud(context.formParam("latitud"));
    }
    if (!ContextHelper.isEmpty(context, "latitud")) {
      direccionInputDto.setLatitud(context.formParam("longitud"));
    }
    if (!ContextHelper.isEmpty(context, "barrio")) {
      direccionInputDto.setBarrio(context.formParam("barrio"));
    }
    if (!ContextHelper.isEmpty(context, "calle")) {
      direccionInputDto.setCalle(context.formParam("calle"));
    }
    if (!ContextHelper.isEmpty(context, "numero")) {
      direccionInputDto.setNumero(context.formParam("numero"));
    }
    if (!ContextHelper.isEmpty(context, "ciudad")) {
      direccionInputDto.setCiudad(context.formParam("ciudad"));
    }
    if (!ContextHelper.isEmpty(context, "provincia")) {
      direccionInputDto.setProvincia(context.formParam("provincia"));
    }
    if (!ContextHelper.isEmpty(context, "nombreUbicacion")) {
      direccionInputDto.setNombreUbicacion(context.formParam("nombreUbicacion"));
    }

    return direccionInputDto;
  }
}
