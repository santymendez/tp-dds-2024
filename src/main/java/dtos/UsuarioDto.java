package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase UsuarioDto.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
  String nombreUsuario;
  String contrasenia;
}
