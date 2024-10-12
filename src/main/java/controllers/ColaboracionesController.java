package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.heladera.Heladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.HeladerasRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de Colaboraciones.
 */

public class ColaboracionesController implements InterfaceCrudViewsHandler {

  private final HeladerasRepository heladerasRepository;

  public ColaboracionesController(HeladerasRepository heladerasRepository) {
    this.heladerasRepository = heladerasRepository;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    List<Heladera> heladeras = heladerasRepository.buscarActivas();
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Colaborar");
    model.put("heladeras", heladeras);
    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

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
