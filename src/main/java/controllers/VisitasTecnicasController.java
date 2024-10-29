package controllers;

import dtos.VisitaInputDto;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.heladera.Heladera;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.IncidentesRepository;
import services.VisitasTecnicasService;
import utils.helpers.ContextHelper;
import utils.helpers.DateHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Representa al controlador del repositorio de visitas.
 */

public class VisitasTecnicasController implements InterfaceCrudViewsHandler {
  private final HeladerasRepository heladerasRepository;
  private final GenericRepository genericRepository;
  private final VisitasTecnicasService visitasTecnicasService;
  private final IncidentesRepository incidentesRepository;

  /**
   * Constructor del controlador de visitas técnicas.
   *
   * @param heladerasRepository repositorio de heladeras.
   * @param genericRepository repositorio generico.
   * @param visitasTecnicasService service de visitas técnicas.
   */

  public VisitasTecnicasController(
      HeladerasRepository heladerasRepository,
      GenericRepository genericRepository,
      VisitasTecnicasService visitasTecnicasService,
      IncidentesRepository incidentesRepository
  ) {
    this.heladerasRepository = heladerasRepository;
    this.genericRepository = genericRepository;
    this.visitasTecnicasService = visitasTecnicasService;
    this.incidentesRepository = incidentesRepository;
  }


  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    model.put("titulo", "Visitas");

    List<Heladera> heladeras = this.heladerasRepository.buscarInactivas();
    model.put("heladeras", heladeras);

    List<Incidente> incidentes = this.incidentesRepository.buscarNoSolucionados();
    model.put("incidentes", incidentes);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("registrar-visita.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    VisitaInputDto visitaInputDto = VisitaInputDto.fromContext(context);

    if (DateHelper.validate(visitaInputDto.getFechaVisita())) {
      context.redirect("/heladeras-solidarias/registrar-visita?invalidDate=true");
      return;
    }

    Long id = Long.parseLong(Objects.requireNonNull(context.formParam("incidente")));
    Incidente incidente = this.genericRepository.buscarPorId(id, Incidente.class).get();

    Tecnico tecnico = ContextHelper.getTecnicoFromContext(context).get();

    this.visitasTecnicasService.crear(visitaInputDto, tecnico, incidente);

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