package controllers;

import dtos.VisitaInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.heladera.Heladera;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.HeladerasRepository;
import services.VisitasTecnicasService;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Representa al controlador del repositorio de visitas.
 */

public class VisitasTecnicasController implements InterfaceCrudViewsHandler {
  private final HeladerasRepository heladerasRepository;
  private final GenericRepository genericRepository;
  private final VisitasTecnicasService visitasTecnicasService;

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
      VisitasTecnicasService visitasTecnicasService
  ) {
    this.heladerasRepository = heladerasRepository;
    this.genericRepository = genericRepository;
    this.visitasTecnicasService = visitasTecnicasService;
  }


  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    model.put("titulo", "Visitas");

    List<Heladera> heladeras = this.heladerasRepository.buscarInactivas();
    model.put("heladeras", heladeras);

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

    Long id = Long.parseLong(context.formParam("idIncidente"));
    Optional<Incidente> posibleIncidente = this.genericRepository.buscarPorId(id, Incidente.class);

    if (posibleIncidente.isEmpty()) {
      context.redirect("/heladeras-solidarias/registrar-visita?errorID=true");
      return;
    }

    Incidente incidente = posibleIncidente.get();

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