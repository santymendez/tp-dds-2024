package controllers;

import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.direccion.Provincia;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import services.VulnerablesService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para el registro de vulnerables.
 */

public class VulnerablesController implements InterfaceCrudViewsHandler {

  private final GenericRepository vulnerablesRepository;
  private final VulnerablesService vulnerablesService;

  public VulnerablesController(
      GenericRepository vulnerablesRepository,
      VulnerablesService vulnerablesService
  ) {
    this.vulnerablesRepository = vulnerablesRepository;
    this.vulnerablesService = vulnerablesService;
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

    context.render("/registrar-vulnerable.hbs", model);
  }

  @Override
  public void save(Context context) {

    VulnerableInputDto vulnerableInputDto = new VulnerableInputDto();
    vulnerableInputDto.setNombre(context.formParam("nombre"));
    vulnerableInputDto.setFechaNacimiento(context.formParam("fechaNacimiento"));
    vulnerableInputDto.setTipoDocumento(context.formParam("tipoDocumento"));
    vulnerableInputDto.setNumeroDocumento(context.formParam("numeroDocumento"));
    vulnerableInputDto.setProvincia(context.formParam("provincia"));
    vulnerableInputDto.setCiudad(context.formParam("ciudad"));
    vulnerableInputDto.setBarrio(context.formParam("barrio"));
    vulnerableInputDto.setCalle(context.formParam("calle"));
    vulnerableInputDto.setNumero(context.formParam("numeroCalle"));
    vulnerableInputDto.setCantMenores(context.formParam("cantMenores"));

    //TODO LOGICA DE MENORES A CARGO

    try {
      Vulnerable vulnerable = this.vulnerablesService.crear(vulnerableInputDto);
      this.vulnerablesRepository.guardar(vulnerable);
      context.redirect("/heladeras-solidarias");
    } catch (Exception e) {
      context.status(500).result("Error al guardar el vulnerable: " + e.getMessage());
    }
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
