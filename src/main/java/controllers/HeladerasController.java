package controllers;

import dtos.DireccionInputDto;
import dtos.HeladeraInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.heladera.Heladera;
import models.entities.personas.users.TipoRol;
import models.repositories.imp.GenericRepository;
import services.DireccionesService;
import services.HeladerasService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de heladeras para alta, baja y modificacion.
 */

public class HeladerasController implements InterfaceCrudViewsHandler {
  private final GenericRepository genericRepository;
  private final HeladerasService heladerasService;
  private final DireccionesService direccionesService;

  /**
   * Constructor de la clase.
   *
   * @param genericRepository Repositorio generico.
   * @param heladerasService Servicio de heladeras.
   * @param direccionesService Servicio de direcciones.
   */

  public HeladerasController(
      GenericRepository genericRepository,
      HeladerasService heladerasService,
      DireccionesService direccionesService
  ) {
    this.genericRepository = genericRepository;
    this.heladerasService = heladerasService;
    this.direccionesService = direccionesService;
  }

  /**
   * Muestra la lista de heladeras.
   *
   * @param context el contexto de la aplicación.
   */

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    model.put("titulo", "Heladeras");

    List<Heladera> heladeras = this.genericRepository.buscarTodos(Heladera.class);
    model.put("heladeras", heladeras);

    List<Provincia> provincias = this.genericRepository.buscarTodos(Provincia.class);
    model.put("provincias", provincias);

    model.put("activeSession", true);
    model.put("tipo_rol", context.sessionAttribute("tipo_rol"));

    if (TipoRol.valueOf(context.sessionAttribute("tipo_rol")).equals(TipoRol.ADMINISTRADOR)) {
      context.render("heladeras-admin.hbs", model);
    } else {
      context.render("heladeras.hbs", model);
    }
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
    HeladeraInputDto heladeraInputDto = HeladeraInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Direccion direccion = this.direccionesService.crear(direccionInputDto);
    this.heladerasService.crear(heladeraInputDto, direccion);

    context.redirect("/heladeras-solidarias");
  }

  /**
   * Muestra un formulario para editar una heladera.
   *
   * @param context el contexto de la aplicación.
   */

  public void edit(Context context) {

  }

  /**
   * Actualiza una heladera en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void update(Context context) {
    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));

    Optional<Heladera> posibleHeladeraBuscada = this
        .genericRepository.buscarPorId(heladeraId, Heladera.class);

    Heladera heladera = posibleHeladeraBuscada.get();

    HeladeraInputDto heladeraInputDto = HeladeraInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    if (direccionInputDto != null) {
      Direccion direccion = this.direccionesService.crear(direccionInputDto);
      heladera.setDireccion(direccion);
    }

    //TODO no tenemos nada para hacer con la razon?
    //String razonModificacion = context.formParam("razonModificacion");

    this.heladerasService.modificar(heladera, heladeraInputDto);

    context.redirect("/heladeras-solidarias");
  }

  /**
   * Elimina una heladera de la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void delete(Context context) {
    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));

    Optional<Heladera> posibleHeladeraBuscada = this
        .genericRepository.buscarPorId(heladeraId, Heladera.class);

    //TODO QUE HACEMOS CON ESTO?
    String descripcion = context.formParam("descripcion");

    posibleHeladeraBuscada.ifPresent(genericRepository::eliminar);
  }
}
