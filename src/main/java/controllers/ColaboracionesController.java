package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.direccion.Provincia;
import models.entities.heladera.Heladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.HeladerasRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de Colaboraciones.
 */

public class ColaboracionesController implements InterfaceCrudViewsHandler {

  private final HeladerasRepository heladerasRepository;
  private final GenericRepository genericRepository;

  public ColaboracionesController(HeladerasRepository heladerasRepository,
                                  GenericRepository genericRepository) {
    this.heladerasRepository = heladerasRepository;
    this.genericRepository = genericRepository;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Colaborar");

    List<Heladera> heladeras = this.heladerasRepository.buscarActivas();
    model.put("heladeras", heladeras);

    List<Provincia> provincias = this.genericRepository.buscarTodos(Provincia.class);
    model.put("provincias", provincias);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("/colaborar.hbs", model);
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
