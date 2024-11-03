package controllers;

import dtos.UsuarioInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import models.entities.personas.users.Usuario;
import models.repositories.imp.UsuariosRepository;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.metrics.TransactionStatus;
import utils.security.PasswordHasher;

/**
 * Controlador de la vista de inicio de sesión.
 */

public class IniciarSesionController implements InterfaceCrudViewsHandler {

  private final UsuariosRepository usuariosRepository;

  public IniciarSesionController(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  @Override
  public void index(Context context) {
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Iniciar Sesion");
    context.render("iniciar-sesion.hbs", model);
  }

  @Override
  public void save(Context context) {
    if (context.sessionAttribute("idUsuario") != null) {
      context.redirect("/heladeras-solidarias");
      context.sessionAttribute("loginStatus", TransactionStatus.REJECTED);
      return;
    }

    UsuarioInputDto usuarioInputDto = new UsuarioInputDto(
            context.formParam("nombreUsuario"),
            context.formParam("contrasenia")
    );

    Optional<Usuario> posibleUsuario =
            usuariosRepository.buscarPorNombreDeUsuario(usuarioInputDto.getNombreUsuario());

    if (posibleUsuario.isPresent() && PasswordHasher
        .validatePassword(usuarioInputDto.getContrasenia(), posibleUsuario.get().getContrasenia())
    ) {
      Usuario usuario = posibleUsuario.get();

      context.sessionAttribute("idUsuario", usuario.getId());
      context.sessionAttribute("tipoRol", usuario.getTipoRol().toString());
      context.sessionAttribute("loginStatus", TransactionStatus.SUCCESS);
      context.redirect("/heladeras-solidarias");
    } else {
      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Iniciar Sesion");
      model.put("error", "Usuario y/o Contraseña Incorrectos");
      context.sessionAttribute("loginStatus", TransactionStatus.ERROR);
      context.render("iniciar-sesion.hbs", model);
    }
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    context.sessionAttribute("idUsuario", null);
    context.sessionAttribute("tipoRol", null);
    context.redirect("/heladeras-solidarias");
  }

  @Override
  public void delete(Context context) {

  }
}