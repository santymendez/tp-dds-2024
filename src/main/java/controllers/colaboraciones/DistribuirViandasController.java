package controllers.colaboraciones;


import dtos.DistribucionViandasDto;
import io.javalin.http.Context;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.TarjetasColaboradoresRepository;
import services.ColaboracionesService;
import services.DistribucionViandasService;
import utils.ColaboracionesHelper;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarViandasController.
 */

public class DistribuirViandasController implements InterfaceCrudViewsHandler {
  private final GenericRepository genericRepository;
  private final DistribucionViandasService distribucionViandasService;
  private final ColaboracionesService colaboracionesService;
  private final TarjetasColaboradoresRepository tarjetasColaboradoresRepository;

  /**
   * Constructor del controller de distribucion de viandas.
   *
   * @param genericRepository repositorio generico.
   * @param distribucionViandasService service de distribucion de viandas.
   */

  public DistribuirViandasController(
      GenericRepository genericRepository,
      DistribucionViandasService distribucionViandasService,
      ColaboracionesService colaboracionesService,
      TarjetasColaboradoresRepository tarjetasColaboradoresRepository
  ) {
    this.genericRepository = genericRepository;
    this.distribucionViandasService = distribucionViandasService;
    this.colaboracionesService = colaboracionesService;
    this.tarjetasColaboradoresRepository = tarjetasColaboradoresRepository;
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

    Optional<TarjetaColaborador> posibleTarjeta =
        this.tarjetasColaboradoresRepository.buscarPorIdColaborador(colaborador.getId());

    TarjetaColaborador tarjetaColaborador = null;

    if(posibleTarjeta.isPresent()) {
      tarjetaColaborador = posibleTarjeta.get();
    }

    if (tarjetaColaborador == null) {
      //TODO
      //COMO PUSIMOS QUE SI TIENE DIRECCION, SE ENVIA LA TARJETA
      //SI NO TIENE TARJETA TENEMOS QUE PEDIRLE LA DIRECCION PARA MANDARSELA
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
