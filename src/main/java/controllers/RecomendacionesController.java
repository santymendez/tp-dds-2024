package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    double lat;
    double lon;

    try {
      String body = context.body();

      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Object> jsonMap = objectMapper.readValue(body, Map.class);

      lat = (double) jsonMap.get("lat");
      lon = (double) jsonMap.get("lon");

      System.out.println("Latitud prueba: " + lat + " Longitud prueba: " + lon);

      String latitud = String.valueOf(lat);
      String longitud = String.valueOf(lon);
      String radio = "3";

      ListadoPuntos listadoPuntos = adapterRecomendacion.puntos(latitud, longitud, radio);

      List<Punto> puntos = listadoPuntos.getPuntos();

      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Recomendacion de Puntos");
      model.put("puntos", puntos);
      model.put("activeSession", true);
      model.put("tipoRol", context.sessionAttribute("tipoRol"));

      context.render("recomendaciones.hbs", model);

    } catch (Exception e) {
      context.status(400).render("/error-base.hbs", ErrorHelper.generateError(
          400,
          "Localizacion no encontrada",
          "No logramos utilizar tu ubicacion"));
    }

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
