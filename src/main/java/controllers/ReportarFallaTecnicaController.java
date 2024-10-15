package controllers;

import dtos.IncidenteInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.heladera.Heladera;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.colaborador.Colaborador;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.searchers.BuscadorTecnicosCercanos;
import services.IncidentesService;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para los incidentes.
 */
public class ReportarFallaTecnicaController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final IncidentesService incidentesService;
  private final BuscadorTecnicosCercanos buscadorTecnicosCercanos;
  private final ReportesHeladerasRepository reportesRepository;

  /**
   * Metodo constructor del controller.
   *
   * @param genericRepository repositorio generico.
   * @param incidentesService un service de incidentes.
   * @param buscadorTecnicosCercanos un buscador de tecnicos cercanos.
   * @param reportesRepository un repositorio de reportes.
   */

  public ReportarFallaTecnicaController(
      GenericRepository genericRepository,
      IncidentesService incidentesService,
      BuscadorTecnicosCercanos buscadorTecnicosCercanos,
      ReportesHeladerasRepository reportesRepository
  ) {
    this.genericRepository = genericRepository;
    this.incidentesService = incidentesService;
    this.buscadorTecnicosCercanos = buscadorTecnicosCercanos;
    this.reportesRepository = reportesRepository;
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

    List<Heladera> heladeras = this.genericRepository.buscarTodos(Heladera.class);
    model.put("heladeras", heladeras);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("/reportar-falla-tecnica.hbs", model);
  }

  @Override
  public void save(Context context) {
    IncidenteInputDto incidenteDto = IncidenteInputDto.fromContext(context);

    Long heladeraId = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));
    Heladera heladera = this.genericRepository.buscarPorId(heladeraId, Heladera.class).get();

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    this.incidentesService.crear(incidenteDto, heladera, colaborador);

    this.buscadorTecnicosCercanos.notificarTecnicos(heladera);

    heladera.intentarNotificarSuscriptores();

    ReporteHeladera reporteHeladera =
        this.reportesRepository.buscarSemanalPorHeladera(heladeraId).get();
    reporteHeladera.ocurrioUnaFalla();
    this.reportesRepository.modificar(reporteHeladera);

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
