package controllers.colaboraciones;

import dtos.DireccionInputDto;
import dtos.HeladeraInputDto;
import io.javalin.http.Context;
import java.util.Objects;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import services.DireccionesService;
import services.HeladerasService;
import services.ReportesHeladerasService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.helpers.DateHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para la colaboracion hacerse cargo de una heladera.
 */

public class HacerseCargoController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final DireccionesService direccionesService;
  private final ColaboracionesService colaboracionesService;
  private final HeladerasService heladerasService;
  private final ReportesHeladerasService reportesHeladerasService;

  /**
   * Constructor del controller de hacerse cargo de una heladera.
   *
   * @param genericRepository un repositorio generico.
   * @param direccionesService servicio de direcciones.
   * @param colaboracionesService servicio de colaboraciones.
   * @param heladerasService servicio de heladeras.
   * @param reportesHeladerasService servicio de r
   */

  public HacerseCargoController(
      GenericRepository genericRepository,
      DireccionesService direccionesService,
      ColaboracionesService colaboracionesService,
      HeladerasService heladerasService,
      ReportesHeladerasService reportesHeladerasService
  ) {
    this.genericRepository = genericRepository;
    this.direccionesService = direccionesService;
    this.colaboracionesService = colaboracionesService;
    this.heladerasService = heladerasService;
    this.reportesHeladerasService = reportesHeladerasService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    HeladeraInputDto heladeraInputDto = HeladeraInputDto.fromContext(context);

    if (DateHelper.validate(heladeraInputDto.getFechaCreacion())) {
      context.redirect("/heladeras-solidarias/colaborar?invalidDate=true");
      return;
    }

    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);
    Direccion direccion = this.direccionesService.crear(direccionInputDto);

    Long idModelo = Long.parseLong(Objects.requireNonNull(context.formParam("modelo")));
    Modelo modelo = this.genericRepository.buscarPorId(idModelo, Modelo.class).get();

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Heladera heladera = this.heladerasService.crear(heladeraInputDto, direccion, modelo);

    Colaboracion colaboracion = this.colaboracionesService.crear(heladera, direccion, colaborador);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    this.reportesHeladerasService.crear(heladera);

    context.redirect("/heladeras-solidarias?colabSuccess=true");
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
