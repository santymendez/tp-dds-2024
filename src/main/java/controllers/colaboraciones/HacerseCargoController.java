package controllers.colaboraciones;

import dtos.DireccionInputDto;
import io.javalin.http.Context;
import java.util.Objects;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import services.DireccionesService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para la colaboracion hacerse cargo de una heladera.
 */

public class HacerseCargoController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final DireccionesService direccionesService;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor del controller de hacerse cargo de una heladera.
   *
   * @param genericRepository un repositorio generico.
   * @param direccionesService servicio de direcciones.
   * @param colaboracionesService servicio de colaboraciones.
   */

  public HacerseCargoController(GenericRepository genericRepository,
                                 DireccionesService direccionesService,
                                 ColaboracionesService colaboracionesService) {
    this.genericRepository = genericRepository;
    this.direccionesService = direccionesService;
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
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();
    //TODO revisar que no hayamos hecho cualquier cosa a partir de aca
    //TODO filtrar las heladeras segun cuales se pueden hacer cargo
    Heladera heladera =
        genericRepository
            .buscarPorId(Long.parseLong(context.formParam("heladera")), Heladera.class)
            .get();

    boolean usarDireccionPropia = Objects.equals(context.formParam("direccionPropia"), "true");

    Direccion direccion;

    if (usarDireccionPropia) {
      direccion = colaborador.getDireccion();
    } else {
      DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);
      direccion = direccionesService.crear(direccionInputDto);
    }

    Colaboracion colaboracion = this.colaboracionesService.crear(heladera, direccion, colaborador);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

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
