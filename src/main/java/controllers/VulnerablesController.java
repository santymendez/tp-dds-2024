package controllers;

import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboradoresRepository;
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
  private final ColaboradoresRepository colaboradoresRepository;

  /**
   * Construcctor de la clase.
   *
   * @param vulnerablesRepository el repo de vulnerables.
   * @param vulnerablesService    el sevice de vulnerables.
   * @param direccionesService    el service de direcciones.
   */

  public VulnerablesController(
      GenericRepository vulnerablesRepository,
      ColaboradoresRepository colaboradoresRepository,
      VulnerablesService vulnerablesService,
      DireccionesService direccionesService
  ) {
    this.vulnerablesRepository = vulnerablesRepository;
    this.colaboradoresRepository = colaboradoresRepository;
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

    List<Provincia> provincias = this.vulnerablesRepository.buscarTodos(Provincia.class);
    model.put("provincias", provincias);

    model.put("activeSession", true);
    model.put("tipo_rol", context.sessionAttribute("tipo_rol"));

    context.render("/registrar-vulnerable.hbs", model);
  }

  @Override
  public void save(Context context) {
    VulnerableInputDto vulnerableInputDto = VulnerableInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Optional<Colaborador> posibleColaborador = this.colaboradoresRepository.buscarPorIdUsuario(
        context.sessionAttribute("idUsuario")
    );

    Colaborador colaborador = posibleColaborador.get();

    //TODO LOGICA DE MENORES A CARGO

    /* EJEMPLO PARA ACCEDER A LOS DATOS DE LOS MENORES A CARGO:

    Menor 2:

    menores[2][nombre] → El nombre del segundo menor.
    menores[2][fechaNacimiento] → La fecha de nacimiento del segundo menor.
    menores[2][tipoDocumento] → El tipo de documento del segundo menor.
    menores[2][numeroDocumento] → El número de documento del segundo menor.
    */

    if (direccionInputDto != null) {
      Direccion direccion = this.direccionesService.crear(direccionInputDto);
      this.vulnerablesService.crear(vulnerableInputDto, direccion, colaborador);
    } else {
      this.vulnerablesService.crear(vulnerableInputDto, colaborador);
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
