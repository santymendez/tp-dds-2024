package controllers;

import config.ServiceLocator;
import config.UtilsLocator;
import dtos.UsuarioDto;
import io.javalin.http.Context;
import java.util.Map;
import java.util.Optional;
import models.entities.personas.users.Usuario;
import models.repositories.imp.UsuariosRepository;
import services.UsuariosService;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.security.Autenticador;

/**
 * Controller de la vista de registro de usuario.
 * (registrarse-usuario.hbs).
 */

public class RegistrarUsuarioController implements InterfaceCrudViewsHandler {

  private final UsuariosRepository usuariosRepository;
  private final UsuariosService usuariosService;
  private final Autenticador autenticador;

  public RegistrarUsuarioController(UsuariosRepository usuariosRepository,
                                    UsuariosService usuariosService, Autenticador autenticador) {
    this.usuariosRepository = usuariosRepository;
    this.usuariosService = usuariosService;
    this.autenticador = autenticador;
  }

  @Override
  public void index(Context context) {
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    context.render("/registrarse-usuario.hbs", Map.of("titulo", "Registrarse"));
  }

  @Override
  public void save(Context context) {
    if (context.sessionAttribute("idUsuario") != null) {
      context.redirect("/heladeras-solidarias");
      return;
    }

    UsuarioDto usuarioDto = new UsuarioDto(
        context.formParam("nombreUsuario"),
        context.formParam("contrasenia")
    );

    //Se valida que no existe ese nombre de usuario
    Optional<Usuario> posibleUsuario =
        usuariosRepository.buscarPorNombreDeUsuario(usuarioDto.getNombreUsuario());

    if (posibleUsuario.isPresent()) {
      context.attribute(
          "error",
          "Nombre de usuario ya registrado."
      );
      context.redirect("/heladeras-solidarias/registrarse-usuario");
      return;
    }

    if (
        !autenticador.esValida(usuarioDto.getContrasenia())
    ) {
      context.attribute(
          "error",
          "Contraseña invalida.\n Motivo:\n" + autenticador.mostrarMensajesConFormato()
      );
      context.redirect("/heladeras-solidarias/registrarse-usuario");
      return;
    }

    //Se crea y se guarda el usuario
    Usuario nuevoUsuario = usuariosService.crear(usuarioDto);
    context.sessionAttribute("idUsuario", nuevoUsuario.getId());
    usuariosRepository.guardar(nuevoUsuario);

    //Se redirige a la creacion de colaborador
    context.redirect("/heladeras-solidarias/registrarse");
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
