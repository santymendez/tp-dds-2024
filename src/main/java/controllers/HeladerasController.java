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
  private final GenericRepository repositorioDeHeladeras;

  public HeladerasController(GenericRepository repo) {
    this.repositorioDeHeladeras = repo;
  }

  //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
  @Override
  public void index(Context context) {

  }

  //RECIBE POR PATH PARAM EL ID DE UN PRODUCTO Y PRETENDE DEVOLVER UNA
  // VISTA CON EL DETALLE DE ESE PRODUCTO
  @Override
  public void show(Context context) {
    Optional<Heladera> posibleHeladeraBuscada = this.repositorioDeHeladeras
        .buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    /*TODO
    if (posibleProductoBuscado.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }*/

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Heladeras");
    model.put("producto", posibleHeladeraBuscada.get());

    context.render("heladeras-colaborador.hbs", model);
  }

  //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UN NUEVO PRODUCTO.

  @Override
  public void create(Context context) {



    context.render("productos/formulario_producto.hbs");
  }

  @Override
  public void save(Context context) {
    //Heladera nuevaHeladera = new Heladera();

    ////TODO (aca deberia setearle los atributos al la heladera)

    //this.repositorioDeHeladeras.guardar(nuevaHeladera);
    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS

    // TODO Duplicar la misma pantalla pero con un script abajo con la alerta!
    context.redirect("/heladerasSolidarias/heladerasAdmin");
  }

  @Override
  public void edit(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE
    // PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
    Optional<Heladera> posibleHeladeraBuscada = this
        .repositorioDeHeladeras.buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    /*TODO
    if (posibleHeladeraBuscada.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }*/

    Map<String, Object> model = new HashMap<>();

    posibleHeladeraBuscada.ifPresent(heladera -> model.put("producto", heladera));

    context.render("productos/detalle_producto.hbs", model);
  }

  @Override
  public void update(Context context) {
    Optional<Heladera> posibleHeladeraBuscada = this
        .repositorioDeHeladeras.buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    posibleHeladeraBuscada.ifPresent(repositorioDeHeladeras::modificar);
  }

  @Override
  public void delete(Context context) {
    Optional<Heladera> posibleHeladeraBuscada = this
        .repositorioDeHeladeras.buscarPorId(Long.valueOf(context.pathParam("id")), Heladera.class);

    posibleHeladeraBuscada.ifPresent(repositorioDeHeladeras::eliminar);
  }
}
