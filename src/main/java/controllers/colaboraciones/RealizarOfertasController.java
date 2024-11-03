package controllers.colaboraciones;

import dtos.OfertaInputDto;
import io.javalin.http.Context;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.helpers.UploadedFilesHelper;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.metrics.TransactionStatus;

/**
 * Controlador de realizar ofertas.
 */

public class RealizarOfertasController implements InterfaceCrudViewsHandler {
  private final GenericRepository ofertasRepository;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor de la clase.
   *
   * @param ofertasRepository repositorio de ofertas.
   * @param colaboracionesService servicio de colaboraciones.
   */

  public RealizarOfertasController(
      GenericRepository ofertasRepository,
      ColaboracionesService colaboracionesService
  ) {
    this.ofertasRepository = ofertasRepository;
    this.colaboracionesService = colaboracionesService;
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
    OfertaInputDto ofertaInputDto = OfertaInputDto.fromContext(context);

    String path = UploadedFilesHelper.getImageFromContext(context);

    ofertaInputDto.setImagenIlustrativa(path);

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Colaboracion colaboracion = this.colaboracionesService.crear(ofertaInputDto, colaborador);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    context.sessionAttribute("colabStatus", TransactionStatus.SUCCESS);
    context.redirect("/heladeras-solidarias/canjear-puntos");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this.ofertasRepository
        .buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(this.ofertasRepository::modificar);
  }

  @Override
  public void delete(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this.ofertasRepository
        .buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(this.ofertasRepository::eliminar);
  }
}
