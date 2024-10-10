package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.heladera.Heladera;
import models.repositories.imp.GenericRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para la visualizacion del mapa.
 */

public class MapaController implements InterfaceCrudViewsHandler {

  private final GenericRepository repositorioDeHeladeras;

  public MapaController(GenericRepository repo) {
    this.repositorioDeHeladeras = repo;
  }

  @Override
  public void index(Context context) {
    List<Heladera> heladeras = this.repositorioDeHeladeras.buscarTodos(Heladera.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Mapa Heladeras");
    model.put("heladeras", heladeras);
    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    System.out.println(model);

    context.render("mapa.hbs", model);
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
