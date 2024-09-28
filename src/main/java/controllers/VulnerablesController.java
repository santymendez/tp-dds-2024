package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para el registro de vulnerables.
 */

public class VulnerablesController implements InterfaceCrudViewsHandler {
  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Registrar Vulnerable");
    context.render("/registrar-vulnerable.hbs");
  }

  @Override
  public void save(Context context) {
    context.redirect("/heladerasSolidarias");
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
