package controllers;

import dtos.DireccionInputDto;
import dtos.HeladeraInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.users.TipoRol;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.IncidentesRepository;
import services.DireccionesService;
import services.HeladerasService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de heladeras para alta, baja y modificacion.
 */

public class HeladerasController implements InterfaceCrudViewsHandler {
  private final GenericRepository genericRepository;
  private final IncidentesRepository incidentesRepository;
  private final HeladerasService heladerasService;
  private final DireccionesService direccionesService;

  /**
   * Constructor de la clase.
   *
   * @param genericRepository Repositorio generico.
   * @param incidentesRepository Repositorio de incidentes.
   * @param heladerasService Servicio de heladeras.
   * @param direccionesService Servicio de direcciones.
   */

  public HeladerasController(
      GenericRepository genericRepository,
      IncidentesRepository incidentesRepository,
      HeladerasService heladerasService,
      DireccionesService direccionesService
  ) {
    this.genericRepository = genericRepository;
    this.incidentesRepository = incidentesRepository;
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

    List<Incidente> alertas = incidentesRepository.buscarAlertas();
    model.put("alertas", alertas);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    if (TipoRol.valueOf(context.sessionAttribute("tipoRol")).equals(TipoRol.ADMINISTRADOR)) {
      context.render("heladeras-admin.hbs", model);
    } else {
      context.render("heladeras.hbs", model);
    }
  }

  public void show(Context context) {

  }

  public void create(Context context) {

  }

  public void save(Context context) {

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

  //TODO MODELO
  public void update(Context context) {
    HeladeraInputDto heladeraInputDto = HeladeraInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));
    Heladera heladera = this.genericRepository.buscarPorId(heladeraId, Heladera.class).get();


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

  //TODO MODELO
  public void delete(Context context) {
    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));

    Heladera heladera = this.genericRepository.buscarPorId(heladeraId, Heladera.class).get();

    //TODO QUE HACEMOS CON ESTO?
    String descripcion = context.formParam("descripcion");

    this.genericRepository.eliminar(heladera);

    context.redirect("/heladeras-solidarias");
  }
}
