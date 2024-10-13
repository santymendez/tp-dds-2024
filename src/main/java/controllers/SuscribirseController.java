package controllers;

import dtos.SuscripcionInputDto;
import io.javalin.http.Context;
import java.util.List;
import java.util.Objects;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.suscripcion.Suscripcion;
import models.repositories.imp.HeladerasRepository;
import services.SuscripcionesService;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;


/**
 * Controlador destinado a la gestion de suscripciones de heladeras.
 */

public class SuscribirseController implements InterfaceCrudViewsHandler {

  private final HeladerasRepository heladerasRepository;
  private final SuscripcionesService suscripcionesService;

  public SuscribirseController(
      HeladerasRepository heladerasRepository,
      SuscripcionesService suscripcionesService
  ) {
    this.heladerasRepository = heladerasRepository;
    this.suscripcionesService = suscripcionesService;
  }


  public void index(Context context) {
  }

  public void show(Context context) {

  }

  public void create(Context context) {

  }

  /**
   * Guarda una suscripcion en la base de datos.
   *
   * @param context Contexto de la aplicación.
   */

  public void save(Context context) {
    SuscripcionInputDto suscripcionInputDto = SuscripcionInputDto.fromContext(context);

    Long idHeladera = Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));
    Heladera heladera = this.heladerasRepository.buscarPorId(idHeladera).get();

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    List<Suscripcion> suscripciones =
        this.suscripcionesService.crear(suscripcionInputDto, colaborador, heladera);

    suscripciones.forEach(heladera::agregarSuscripcion);

    this.heladerasRepository.modificar(heladera);

    context.redirect("/heladeras-solidarias");
  }

  public void edit(Context context) {

  }

  public void update(Context context) {

  }

  public void delete(Context context) {

  }
}
