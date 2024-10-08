package controllers;

import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.repositories.imp.GenericRepository;
import services.DireccionesService;
import services.VulnerablesService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para el registro de vulnerables.
 */

public class VulnerablesController implements InterfaceCrudViewsHandler {

  private final GenericRepository vulnerablesRepository;
  private final VulnerablesService vulnerablesService;
  private final DireccionesService direccionesService;

  /** Construcctor de la clase.
   *
   * @param vulnerablesRepository el repo de vulnerables.
   * @param vulnerablesService el sevice de vulnerables.
   * @param direccionesService el service de direcciones.
   */

  public VulnerablesController(
      GenericRepository vulnerablesRepository,
      VulnerablesService vulnerablesService,
      DireccionesService direccionesService
  ) {
    this.vulnerablesRepository = vulnerablesRepository;
    this.vulnerablesService = vulnerablesService;
    this.direccionesService = direccionesService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    // TODO poner en el init las provincias
    List<Provincia> provincias = this.vulnerablesRepository.buscarTodos(Provincia.class);
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Registrar Vulnerable");
    model.put("provincias", provincias);
    model.put("activeSession", true);
    model.put("tipo_rol", context.sessionAttribute("tipo_rol"));

    context.render("/registrar-vulnerable.hbs", model);
  }

  @Override
  public void save(Context context) {
    VulnerableInputDto vulnerableInputDto = VulnerableInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    //TODO LOGICA DE MENORES A CARGO

    if (direccionInputDto != null) {
      Direccion direccion = this.direccionesService.crear(direccionInputDto);
      this.vulnerablesService.crear(vulnerableInputDto, direccion);
    } else {
      this.vulnerablesService.crear(vulnerableInputDto);
    }

    context.redirect("/heladeras-solidarias");
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
