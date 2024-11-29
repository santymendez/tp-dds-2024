package controllers.colaboraciones;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.metrics.TransactionStatus;

/**
 * Controller para la distribucion de tarjetas.
 */

public class DistribuirTarjetasController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final ColaboracionesService colaboracionesService;

  public DistribuirTarjetasController(
      GenericRepository genericRepository,
      ColaboracionesService colaboracionesService
  ) {
    this.colaboracionesService = colaboracionesService;
    this.genericRepository = genericRepository;
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
    int cantidadTarjetas =
        Integer.parseInt(Objects.requireNonNull(context.formParam("cant_tarjetas")));

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    if (colaborador.getDireccion() == null || !colaborador.getDireccion().admiteEnvio()) {
      context.sessionAttribute("colabStatus", TransactionStatus.RETRY);
      context.redirect("/heladeras-solidarias/agregar-direccion");
      return;
    }

    List<TarjetaVulnerable> tarjetasVulnerables = new ArrayList<>();

    //Se generan las tarjetas en el sistema, que despues van a ser entregadas
    //al colaborador para que pueda registrar vulnerables.
    for (int i = 0; i < cantidadTarjetas; i++) {
      TarjetaVulnerable tarjetaVulnerable = new TarjetaVulnerable();
      tarjetasVulnerables.add(tarjetaVulnerable);
    }

    this.genericRepository.guardarColeccion(tarjetasVulnerables);

    Colaboracion colaboracion = this.colaboracionesService.crear(cantidadTarjetas);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

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
