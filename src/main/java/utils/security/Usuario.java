package utils.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase usuario para generar la autenticacion.
 */

@Setter
@Getter
@AllArgsConstructor
public class Usuario {
  private String usuario;
  private String contrasenia;
}
