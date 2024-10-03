package controllers;

import config.UtilsLocator;
import dtos.UsuarioDto;
import io.javalin.http.Context;
import java.util.Map;
import java.util.Optional;
import models.entities.personas.users.Usuario;
import models.repositories.imp.UsuariosRepository;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.security.Autenticador;

/**
 * Controller de la vista de registro de usuario.
 * (registrarse.hbs).
 */

public class RegistrarUsuarioController implements InterfaceCrudViewsHandler {

  private final UsuariosRepository usuariosRepository;

  public RegistrarUsuarioController(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  @Override
  public void index(Context context) {

    context.render("/registrarse.hbs", Map.of("titulo", "Registrarse"));
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  //TODO gran parte de esto iria en un registrar usuario
  //Esto en realidad me va a registrar el colaborador
  @Override
  public void save(Context context) {
    if (context.sessionAttribute("idUsuario") != null) {
      context.redirect("/heladeras-solidarias");
    }

    UsuarioDto usuarioDto = new UsuarioDto(
        context.formParam("nombreUsuario"),
        context.formParam("contrasenia")
    );

    //TODO validacion campos fueron rellenados (iria en hbs)

    //Se valida que no existe ese nombre de usuario
    Optional<Usuario> posibleUsuario =
        usuariosRepository.buscarPorNombreDeUsuario(usuarioDto.getNombreUsuario());

    if (posibleUsuario.isPresent()) {
      context.attribute(
          "error",
          "Nombre de usuario ya registrado."
      );
      context.redirect("/heladeras-solidarias/registrarse");
    }

    //Se valida la contraseña
    Autenticador autenticador = UtilsLocator.instanceOf(Autenticador.class);
    if (
        !autenticador.esValida(usuarioDto.getContrasenia())
    ) {
      context.attribute(
          "error",
          "Contraseña invalida.\n Motivo:\n" + autenticador.mostrarMensajesConFormato()
      );
      context.redirect("/heladeras-solidarias/registrarse");
    }

    //TODO crear el usuario (service?) y guardarlo en el repo

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
