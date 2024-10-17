package services;

import dtos.UsuarioInputDto;
import models.entities.personas.users.Usuario;
import utils.security.PasswordHasher;

/**
 * Service de la clase usuario.
 */

public class UsuariosService {

  /**
   * Crea un usuario a partir de un DTO.
   *
   * @param usuarioInputDto el DTO del usuario
   * @return el usuario
   */

  public Usuario crear(UsuarioInputDto usuarioInputDto) {
    String hashedPassword = PasswordHasher.hashPassword(usuarioInputDto.getContrasenia());

    return new Usuario(usuarioInputDto.getNombreUsuario(),
        hashedPassword, null);
  }
}
