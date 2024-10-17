package dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase UsuarioDto.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInputDto {
  String nombreUsuario;
  String contrasenia;

  /**
   * Crea un objeto UsuarioDto a partir de un contexto.
   *
   * @param context Contexto.
   * @return UsuarioDto.
   */

  public static UsuarioInputDto fromContext(Context context) {
    return new UsuarioInputDto(
        context.formParam("usuario"),
        context.formParam("contrasenia")
    );
  }
}
