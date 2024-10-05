package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.heladera.Heladera;
import models.repositories.imp.GenericRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de heladeras para alta, baja y modificacion.
 */

public class HeladerasController implements InterfaceCrudViewsHandler {
  private final GenericRepository heladerasRepository;

  public HeladerasController(GenericRepository genericRepository) {
    this.heladerasRepository = genericRepository;
  }

  /**
   * Muestra la lista de heladeras.
   *
   * @param context el contexto de la aplicación.
   */

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    //TODO VER SI SE PUEDE GENERALIZAR PORQUE VA A ESTAR EN TODOS LOS CONTROLLERS
    if (context.sessionAttribute("idUsuario") != null) {
      model.put("activeSession", true);
      model.put("tipo_rol", context.sessionAttribute("tipo_rol"));
    } else {
      model.put("activeSession", false);
      context.redirect("/heladeras-solidarias");
      return;
    }

    model.put("titulo", "Heladeras");
    List<Heladera> heladeras = this.heladerasRepository.buscarTodos(Heladera.class);
    model.put("heladeras", heladeras);

    context.render("heladeras.hbs", model);
  }

  public void show(Context context) {

  }

  public void create(Context context) {

  }

  /**
   * Guarda una heladera en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void save(Context context) {
    //Heladera nuevaHeladera = new Heladera();

    //this.repositorioDeHeladeras.guardar(nuevaHeladera);
    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS

    // TODO Duplicar la misma pantalla pero con un script abajo con la alerta!
    context.redirect("/heladeras-solidarias/heladeras");
  }

  /**
   * Muestra un formulario para editar una heladera.
   *
   * @param context el contexto de la aplicación.
   */

  public void edit(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE
    // PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
    Optional<Heladera> posibleHeladeraBuscada = this
        .heladerasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    /*TODO
    if (posibleHeladeraBuscada.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }*/

    Map<String, Object> model = new HashMap<>();

    posibleHeladeraBuscada.ifPresent(heladera -> model.put("producto", heladera));

    context.render("productos/detalle_producto.hbs", model);
  }

  /**
   * Actualiza una heladera en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void update(Context context) {
    Optional<Heladera> posibleHeladeraBuscada = this
        .heladerasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    posibleHeladeraBuscada.ifPresent(heladerasRepository::modificar);
  }

  /**
   * Elimina una heladera de la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void delete(Context context) {
    Optional<Heladera> posibleHeladeraBuscada = this
        .heladerasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    posibleHeladeraBuscada.ifPresent(heladerasRepository::eliminar);
  }
}
