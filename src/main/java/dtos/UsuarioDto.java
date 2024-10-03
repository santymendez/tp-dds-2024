package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase UsuarioDto.
 */

@Data
@AllArgsConstructor
public class UsuarioDto {
  String nombreUsuario;
  String contrasenia;
}
