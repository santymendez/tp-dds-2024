package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.personas.users.Usuario;
import models.repositories.imp.GenericRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de usuarios.
 */

public class UsuariosController implements InterfaceCrudViewsHandler {

  private final GenericRepository usuariosRepository;

  public UsuariosController(
      GenericRepository usuariosRepository
  ) {
    this.usuariosRepository = usuariosRepository;
  }

  /**
   * Muestra la lista de usuarios.
   *
   * @param context contexto de la aplicacion.
   */

  public void index(Context context) {

    List<Usuario> usuarios = usuariosRepository.buscarTodos(Usuario.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");
    model.put("usuarios", usuarios);

    Long idSesion = context.sessionAttribute("idUsuario");
    if (idSesion != null) {
      model.put("activeSession", true);
      model.put("tipoRol", context.sessionAttribute("tipoRol"));

    } else {
      model.put("activeSession", false);
      context.redirect("/heladeras-solidarias");
      return;
    }

    context.render("usuarios.hbs", model);
  }

  public void show(Context context) {

  }

  public void create(Context context) {
  }

  public void save(Context context) {

  }

  public void edit(Context context) {

  }

  public void update(Context context) {
  }

  public void delete(Context context) {

  }
}
