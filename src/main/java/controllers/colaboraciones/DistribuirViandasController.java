package controllers.colaboraciones;

import dtos.DistribucionViandasInputDto;
import io.javalin.http.Context;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.TarjetasColaboradoresRepository;
import services.ColaboracionesService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.helpers.SolicitudAperturaHelper;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.metrics.TransactionStatus;

/**
 * DonarViandasController.
 */

public class DistribuirViandasController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final ColaboracionesService colaboracionesService;
  private final TarjetasColaboradoresRepository tarjetasColaboradoresRepository;

  /**
   * Constructor del controller de distribucion de viandas.
   *
   * @param genericRepository repositorio generico.
   * @param colaboracionesService service de colaboraciones.
   * @param tarjetasColaboradoresRepository repositorio de tarjetas.
   */

  public DistribuirViandasController(
      GenericRepository genericRepository,
      ColaboracionesService colaboracionesService,
      TarjetasColaboradoresRepository tarjetasColaboradoresRepository
  ) {
    this.genericRepository = genericRepository;
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

    TarjetaColaborador tarjetaColaborador;

    if (posibleTarjeta.isPresent()) {
      tarjetaColaborador = posibleTarjeta.get();
    } else {
      context.sessionAttribute("colabStatus", TransactionStatus.RETRY);
      context.redirect("/heladeras-solidarias/agregar-direccion");
      return;
    }

    DistribucionViandasInputDto distrbucionDto = DistribucionViandasInputDto.fromContext(context);

    Heladera heladeraOrigen = this.genericRepository
        .buscarPorId(Long.parseLong(distrbucionDto.getHeladeraOrigen()), Heladera.class)
        .get();
    Heladera heladeraDestino = this.genericRepository
        .buscarPorId(Long.parseLong(distrbucionDto.getHeladeraDestino()), Heladera.class)
        .get();

    if (heladeraOrigen.equals(heladeraDestino)) {
      context.sessionAttribute("colabStatus", TransactionStatus.ERROR);
      //TODO MODAL ERROR
      return;
    }

    int cantViandas = distrbucionDto.getCantViandasDistribuidas();
    if (cantViandas > heladeraOrigen.consultarStock()
        || !heladeraDestino.hayEspacioPara(cantViandas)) {
      context.redirect("/heladeras-solidarias/colaborar?errorDistribucion=true&espacioDisponible="
          + cantViandas);
      context.sessionAttribute("colabStatus", TransactionStatus.ERROR);
      return;
    }

    Colaboracion colaboracion =
        this.colaboracionesService.crear(distrbucionDto, heladeraOrigen, heladeraDestino);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    Colaboracion colaboracionAux = new Colaboracion(colaboracion);
    colaboracionAux.getDistribucionViandas().setCantViandasDistribuidas(0);

    UsoTarjetaColaborador usoTarjetaColaboradorOrigen = new UsoTarjetaColaborador(colaboracionAux);
    tarjetaColaborador.agregarUso(usoTarjetaColaboradorOrigen, heladeraOrigen);
    heladeraOrigen.habilitarTarjeta(tarjetaColaborador);

    UsoTarjetaColaborador usoTarjetaColaboradorDestino = new UsoTarjetaColaborador(colaboracion);
    tarjetaColaborador.agregarUso(usoTarjetaColaboradorDestino, heladeraDestino);
    heladeraOrigen.habilitarTarjeta(tarjetaColaborador);

    this.genericRepository.modificar(heladeraOrigen);
    this.genericRepository.modificar(heladeraDestino);

    SolicitudAperturaHelper.realizarSolicitud(usoTarjetaColaboradorOrigen);
    SolicitudAperturaHelper.realizarSolicitud(usoTarjetaColaboradorDestino);

    context.sessionAttribute("colabStatus", TransactionStatus.SUCCESS);
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

