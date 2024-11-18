package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.heladera.Heladera;
import models.entities.personas.users.TipoRol;
import models.repositories.imp.HeladerasRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para la visualizacion del mapa.
 */

public class MapaController implements InterfaceCrudViewsHandler {

  private final HeladerasRepository repositorioDeHeladeras;

  public MapaController(HeladerasRepository repo) {
    this.repositorioDeHeladeras = repo;
  }

  @Override
  public void index(Context context) {
    List<Heladera> heladeras;
    heladeras = this.repositorioDeHeladeras.buscarTodos();

    TipoRol tipoRol = TipoRol.valueOf(context.sessionAttribute("tipoRol"));

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Mapa Heladeras");
    model.put("heladeras", heladeras);
    model.put("activeSession", true);
    model.put("tipoRol", tipoRol.toString());

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
