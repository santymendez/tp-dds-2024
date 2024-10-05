package services;

import dtos.UsuarioDto;
import models.entities.personas.users.Usuario;

/**
 * Service de la clase usuario.
 */

public class UsuariosService {

  /**
   * Crea un usuario a partir de un DTO.
   *
   * @param usuarioInput el DTO del usuario
   * @return el usuario
   */

  public Usuario crear(UsuarioDto usuarioInput) {
    //Al m
    return new Usuario(usuarioInput.getNombreUsuario(),
        usuarioInput.getContrasenia(), null);
  }
}
