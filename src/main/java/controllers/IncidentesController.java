package controllers;

import dtos.IncidenteDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.heladera.Heladera;
import models.entities.heladera.incidente.Incidente;
import models.repositories.imp.GenericRepository;
import services.IncidentesService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para los incidentes.
 */
public class IncidentesController implements InterfaceCrudViewsHandler {

  private final GenericRepository incidentesRepository;
  private final IncidentesService incidentesService;

  public IncidentesController(GenericRepository repo,
                              IncidentesService inciService) {
    this.incidentesRepository = repo;
    this.incidentesService = inciService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    List<Heladera> heladeras = incidentesRepository.buscarTodos(Heladera.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Reportar Falla");
    model.put("activeSession", true);
    model.put("tipo_rol", context.sessionAttribute("tipo_rol"));
    model.put("heladeras", heladeras);

    context.render("/reportar-falla-tecnica.hbs", model);
  }

  //TODO terminar de implementar
  @Override
  public void save(Context context) {

    IncidenteDto incidenteDto = new IncidenteDto();
    incidenteDto.setHeladera(context.formParam("heladera"));
    incidenteDto.setImagen(context.formParam("imagen"));
    incidenteDto.setDescripcion(context.formParam("descripcion"));
    incidenteDto.setTipoIncidente("");
    incidenteDto.setMomentoIncidente("");
    incidenteDto.setColaborador("");
    incidenteDto.setTipoAlerta("");

    Incidente incidente = incidentesService.crear(incidenteDto);

    incidentesRepository.guardar(incidente);

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
