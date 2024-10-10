package controllers;

import dtos.IncidenteDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;
import services.IncidentesService;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para los incidentes.
 */
public class ReportarFallaTecnicaController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final IncidentesService incidentesService;

  public ReportarFallaTecnicaController(
      GenericRepository genericRepository,
      IncidentesService incidentesService
  ) {
    this.genericRepository = genericRepository;
    this.incidentesService = incidentesService;
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

    model.put("titulo", "Reportar Falla");
    List<Heladera> heladeras = genericRepository.buscarTodos(Heladera.class);
    model.put("heladeras", heladeras);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("/reportar-falla-tecnica.hbs", model);
  }

  @Override
  public void save(Context context) {
    IncidenteDto incidenteDto = IncidenteDto.fromContext(context);

    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));
    Heladera heladera = this.genericRepository.buscarPorId(heladeraId, Heladera.class).get();

    Long usuarioId = context.sessionAttribute("idUsuario");
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    this.incidentesService.crear(incidenteDto, heladera, colaborador);

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
