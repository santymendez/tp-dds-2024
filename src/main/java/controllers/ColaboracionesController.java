package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de Colaboraciones.
 */

public class ColaboracionesController implements InterfaceCrudViewsHandler {

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Colaborar");
    model.put("activeSession", true);
    model.put("tipo_rol", context.sessionAttribute("tipo_rol"));

    context.render("/colaborar.hbs", model);
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
