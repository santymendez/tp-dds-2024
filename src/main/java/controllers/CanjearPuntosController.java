package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.GenericRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para canjear los puntos.
 */

public class CanjearPuntosController implements InterfaceCrudViewsHandler {

  private final GenericRepository canjesRepository;

  public CanjearPuntosController(GenericRepository repo) {
    this.canjesRepository = repo;
  }

  @Override
  public void index(Context context) {
    List<Oferta> ofertas = canjesRepository.buscarTodos(Oferta.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");
    model.put("ofertas", ofertas);

    context.render("canjear-puntos.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");

    context.render("colaborar.hbs", model);
  }

  //TODO esto va en la pagina de colaborar
  @Override
  public void save(Context context) {
    Oferta nuevaOferta = new Oferta();

    nuevaOferta.setNombre(context.formParam("nombre"));
    nuevaOferta.setPuntosNecesarios(Float
        .valueOf(Objects.requireNonNull(context.formParam("puntosRequeridos"))));
    nuevaOferta.setDescripcion(context.formParam("descripcion"));

    nuevaOferta.setImagenIlustrativa("/imgs/logo.png");

    this.canjesRepository.guardar(nuevaOferta);
    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS

    System.out.println("Redirigiendo a canjear puntos...");
    context.redirect("/heladerasSolidarias/canjear-puntos");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this
        .canjesRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(canjesRepository::modificar);
  }

  @Override
  public void delete(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this
        .canjesRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(canjesRepository::eliminar);
  }
}
