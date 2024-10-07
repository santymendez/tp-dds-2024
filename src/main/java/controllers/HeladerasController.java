package controllers;

import dtos.DireccionInputDto;
import dtos.HeladeraInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;
import services.DireccionesService;
import services.HeladerasService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de heladeras para alta, baja y modificacion.
 */

public class HeladerasController implements InterfaceCrudViewsHandler {
  private final GenericRepository heladerasRepository;
  private final HeladerasService heladerasService;
  private final DireccionesService direccionesService;
  private final DireccionesRepository direccionesRepository;

  /**
   * Constructor de la clase.
   *
   * @param genericRepository Repositorio generico.
   * @param heladerasService Servicio de heladeras.
   * @param direccionesService Servicio de direcciones.
   * @param direccionesRepository Repositorio de direcciones.
   */

  public HeladerasController(
      GenericRepository genericRepository,
      HeladerasService heladerasService,
      DireccionesService direccionesService,
      DireccionesRepository direccionesRepository
  ) {
    this.heladerasRepository = genericRepository;
    this.heladerasService = heladerasService;
    this.direccionesService = direccionesService;
    this.direccionesRepository = direccionesRepository;
  }

  /**
   * Muestra la lista de heladeras.
   *
   * @param context el contexto de la aplicación.
   */

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    model.put("titulo", "Heladeras");
    List<Heladera> heladeras = this.heladerasRepository.buscarTodos(Heladera.class);
    model.put("heladeras", heladeras);
    model.put("activeSession", true);
    model.put("tipo_rol", context.sessionAttribute("tipo_rol"));

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
    HeladeraInputDto heladeraInputDto = context.bodyAsClass(HeladeraInputDto.class);

    DireccionInputDto direccionInputDto = DireccionInputDto.builder()
        .barrio(heladeraInputDto.getBarrio())
        .calle(heladeraInputDto.getCalle())
        .numero(heladeraInputDto.getNumero())
        .latitud(heladeraInputDto.getLatitud())
        .longitud(heladeraInputDto.getLongitud())
        .ciudad(heladeraInputDto.getCiudad())
        .provincia(heladeraInputDto.getProvincia())
        .build();

    Heladera heladera = this.heladerasService.crear(heladeraInputDto);

    Float latitud = Float.valueOf(heladeraInputDto.getLatitud());
    Float longitud = Float.valueOf(heladeraInputDto.getLongitud());

    Optional<Direccion> posibleDireccion =
        this.direccionesRepository.buscarPorLatLong(latitud, longitud);

    if (posibleDireccion.isPresent()) {
      //TODO tirar pantalla de error?
      context.redirect("/heladeras-solidarias/heladeras");
      return;
    }

    Direccion direccion = this.direccionesService.crear(direccionInputDto);
    this.direccionesRepository.guardar(direccion);

    heladera.setDireccion(direccion);
    this.heladerasRepository.guardar(heladera);
    context.redirect("/heladeras-solidarias");
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
