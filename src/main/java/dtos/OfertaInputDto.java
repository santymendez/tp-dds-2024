package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase para el output de las ofertas.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaInputDto {
  private String nombre;
  private String puntosNecesarios;
  private String imagenIlustrativa;
  private String descripcion;

  /**
   * Metodo que genera una oferta dto a partir del contexto.
   *
   * @param context Contexto.
   * @return OfertaInputDto generado.
   */

  public static OfertaInputDto fromContext(Context context) {
    OfertaInputDto nuevaOferta = new OfertaInputDto();
    nuevaOferta.setNombre(context.formParam("nombre"));
    nuevaOferta.setDescripcion(context.formParam("descripcion"));
    nuevaOferta.setPuntosNecesarios(context.formParam("puntosNecesarios"));

    return nuevaOferta;
  }
}
