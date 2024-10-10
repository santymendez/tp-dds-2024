package controllers;

import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import services.DireccionesService;
import services.VulnerablesService;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para el registro de vulnerables.
 */

public class VulnerablesController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final VulnerablesService vulnerablesService;
  private final DireccionesService direccionesService;

  /**
   * Construcctor de la clase.
   *
   * @param vulnerablesRepository el repo de vulnerables.
   * @param vulnerablesService    el sevice de vulnerables.
   * @param direccionesService    el service de direcciones.
   */

  public VulnerablesController(
      GenericRepository vulnerablesRepository,
      VulnerablesService vulnerablesService,
      DireccionesService direccionesService
  ) {
    this.genericRepository = vulnerablesRepository;
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
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Registrar Vulnerable");

    List<Provincia> provincias = this.genericRepository.buscarTodos(Provincia.class);
    model.put("provincias", provincias);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("/registrar-vulnerable.hbs", model);
  }

  @Override
  public void save(Context context) {

    VulnerableInputDto vulnerableInputDto = VulnerableInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Long idUsuario = context.sessionAttribute("idUsuario");

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    int cantMenores = Integer.parseInt(Objects.requireNonNull(context.formParam("cantMenores")));

    Direccion direccion = null;

    if (direccionInputDto != null) {
      direccion = this.direccionesService.crear(direccionInputDto);
      this.genericRepository.guardar(direccion);
    }

    Vulnerable padre =  this.vulnerablesService.crear(vulnerableInputDto, direccion, colaborador);

    for (int i = 1; i <= cantMenores; i++) {
      VulnerableInputDto menorInputDto = VulnerableInputDto.fromContext(context, i);
      this.vulnerablesService.crearMenor(menorInputDto, padre, colaborador);
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
