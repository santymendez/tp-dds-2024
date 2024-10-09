package controllers.colaboraciones;


import dtos.DistribucionViandasDto;
import io.javalin.http.Context;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import services.DistribucionViandasService;
import utils.ColaboracionesHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarViandasController.
 */

public class DistribuirViandasController implements InterfaceCrudViewsHandler {
  private final GenericRepository genericRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  private final DistribucionViandasService distribucionViandasService;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor del controller de distribucion de viandas.
   *
   * @param genericRepository repositorio generico.
   * @param colaboradoresRepository repositorio de colaboradores.
   * @param distribucionViandasService service de distribucion de viandas.
   */

  public DistribuirViandasController(
      GenericRepository genericRepository,
      ColaboradoresRepository colaboradoresRepository,
      DistribucionViandasService distribucionViandasService,
      ColaboracionesService colaboracionesService
  ) {
    this.genericRepository = genericRepository;
    this.colaboradoresRepository = colaboradoresRepository;
    this.distribucionViandasService = distribucionViandasService;
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
    Optional<Colaborador> posibleColaborador =
        this.colaboradoresRepository.buscarPorIdUsuario(context.sessionAttribute("idUsuario"));

    Colaborador colaborador = posibleColaborador.get();

    //TODO chequear que tenga tarjeta para abrir heladeras y manejar error
    if (false) {
      context.attribute("error", "No tiene tarjeta para abrir las heladeras");
      context.redirect("/heladeras-solidarias/colaborar");
      return;
    }

    DistribucionViandasDto distrbucionDto = DistribucionViandasDto.fromContext(context);

    Optional<Heladera> posibleHeladeraOrigen = genericRepository.buscarPorId(
        Long.parseLong(distrbucionDto.getHeladeraOrigen()),
        Heladera.class);
    Optional<Heladera> posibleHeladeraDestino = genericRepository.buscarPorId(
        Long.parseLong(distrbucionDto.getHeladeraDestino()),
        Heladera.class);

    Heladera heladeraOrigen = posibleHeladeraOrigen.get();
    Heladera heladeraDestino = posibleHeladeraDestino.get();

    DistribucionViandas distribucion =
        distribucionViandasService.crear(
            distrbucionDto,
            heladeraOrigen,
            heladeraDestino
        );

    Colaboracion colaboracion = colaboracionesService.crear(distribucion);
    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    //TODO habilitar tarjeta del colaborador para abrir heladeras

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

