package services;

import dtos.UsuarioDto;
import models.entities.personas.users.Usuario;

import java.util.Optional;

/**
 * Service de la clase usuario.
 */

public class UsuariosService {
    public Usuario crear(UsuarioDto usuarioInput) {
        //Al m
        return new Usuario(
                usuarioInput.getNombreUsuario(),
                usuarioInput.getContrasenia(),
                null
        );
    }
}
