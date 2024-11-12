package utils.helpers;

import models.entities.personas.users.TipoRol;
import models.entities.personas.users.Usuario;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Clase Helper para los usuarios.
 */

public class UsuariosHelper {

  /**
   * Crea un usuario random con un rol.
   *
   * @param rol Rol del usuario.
   * @return Usuario con nombre de usuario y contraseña random.
   */

  public static Usuario crearUsuarioRandomConRol(TipoRol rol) {
    String username = RandomStringUtils.randomAlphanumeric(8);
    String password = RandomStringUtils.randomAlphanumeric(12);
    return new Usuario(username, password, rol);
  }
}
