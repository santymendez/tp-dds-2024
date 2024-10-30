package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.helpers.ErrorHelper;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.recomendator.adapter.AdapterServicioRecomendacion;
import utils.recomendator.entities.ListadoPuntos;
import utils.recomendator.entities.Punto;

/**
 * Controller de recomendaciones de heladeras.
 */

public class RecomendacionesController implements InterfaceCrudViewsHandler {

  private final AdapterServicioRecomendacion adapterRecomendacion;

  public RecomendacionesController(AdapterServicioRecomendacion adapterRecomendacion) {
    this.adapterRecomendacion = adapterRecomendacion;
  }


  @Override
  public void index(Context context) {
  }

  @Override
  public void show(Context context) {
    try {
      String lat = context.formParam("lat");
      String lng = context.formParam("lng");
      String rad = context.formParam("rad");

      ListadoPuntos listadoPuntos = this.adapterRecomendacion.puntos(lat, lng, rad);

      List<Punto> puntos = listadoPuntos.getPuntos();

      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Recomendacion de Puntos");
      model.put("puntos", puntos);
      model.put("activeSession", true);
      model.put("tipoRol", context.sessionAttribute("tipoRol"));

      context.render("mapa-recomendacion-puntos.hbs", model);

    } catch (Exception e) {
      context.status(400).render("/error-base.hbs", ErrorHelper.generateError(
          400,
          "Localizacion no encontrada",
          "No logramos utilizar tu ubicacion"));
    }
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
