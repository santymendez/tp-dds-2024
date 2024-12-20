package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de la vista de la página de inicio.
 */

public class HomePageController implements InterfaceCrudViewsHandler {
  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Heladeras Solidarias");
    model.put("activeSession", false);

    if (context.sessionAttribute("idUsuario") != null) {
      model.put("activeSession", true);
      model.put("tipoRol", context.sessionAttribute("tipoRol"));
    }

    context.render("home-page.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

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
