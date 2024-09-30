package controllers.colaboraciones;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.GenericRepository;
import utils.javalin.InterfaceCrudViewsHandler;

public class DonarDineroController implements InterfaceCrudViewsHandler {

  private final GenericRepository colaboracionesRepository;
  //private final OfertasService dineroService; //TODO: Implementar OfertasService

  public DonarDineroController (GenericRepository repo) {
    this.colaboracionesRepository = repo;
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
    model.put("titulo", "dinero");

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
