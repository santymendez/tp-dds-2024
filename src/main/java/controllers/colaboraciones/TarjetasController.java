package controllers.colaboraciones;

import io.javalin.http.Context;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * TarjetasController.
 */

public class TarjetasController implements InterfaceCrudViewsHandler {
  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

    context.redirect("/heladeras-solidarias");
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
