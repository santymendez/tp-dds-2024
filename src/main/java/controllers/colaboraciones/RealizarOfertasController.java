package controllers.colaboraciones;

import dtos.OfertaInputDto;
import io.javalin.http.Context;
import java.util.Objects;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import services.OfertasService;
import utils.ColaboracionesHelper;
import utils.ContextHelper;
import utils.UploadedFilesHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de realizar ofertas.
 */

public class RealizarOfertasController implements InterfaceCrudViewsHandler {
  private final GenericRepository ofertasRepository;
  private final OfertasService ofertasService;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor de la clase.
   *
   * @param ofertasRepository repositorio de ofertas.
   * @param ofertasService servicio de ofertas.
   * @param colaboracionesService servicio de colaboraciones.
   */

  public RealizarOfertasController(
      GenericRepository ofertasRepository,
      OfertasService ofertasService,
      ColaboracionesService colaboracionesService
  ) {
    this.ofertasRepository = ofertasRepository;
    this.ofertasService = ofertasService;
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

    ofertaInputDto.setImagenIlustrativa(Objects.requireNonNullElse(path, "/static-imgs/logo.png"));

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Oferta oferta = this.ofertasService.crear(ofertaInputDto, colaborador);

    Colaboracion colaboracion = this.colaboracionesService.crear(oferta);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    context.redirect("/heladeras-solidarias/canjear-puntos");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this
        .ofertasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(ofertasRepository::modificar);
  }

  @Override
  public void delete(Context context) {
    Optional<Oferta> posibleCanjeBuscado = this
        .ofertasRepository.buscarPorId(Long.valueOf(context.pathParam("id")), Oferta.class);

    posibleCanjeBuscado.ifPresent(ofertasRepository::eliminar);
  }
}