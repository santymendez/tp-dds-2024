package controllers.colaboraciones;

import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarDineroController.
 */

public class DonarDineroController implements InterfaceCrudViewsHandler {

  private final ColaboracionesRepository colaboracionesRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  //private final OfertasService dineroService; //TODO: Implementar OfertasService

  public DonarDineroController(ColaboracionesRepository repo1,
                               ColaboradoresRepository repo2) {
    this.colaboracionesRepository = repo1;
    this.colaboradoresRepository = repo2;
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
    model.put("titulo", "dinero");

    context.render("/colaborar.hbs", model);
  }

  @Override
  public void save(Context context) {
    DonacionDinero donacionDinero = new DonacionDinero();
    donacionDinero.setMontoDonado(Integer.valueOf(Objects
        .requireNonNull(context.formParam("montoDonado"))));
    donacionDinero.setFrecuenciaDonacion(context.formParam("frecuenciaDonacion"));

    //TODO revisar, aca tengo en cuenta que un usuario es unico por
    //colaborador asi que lo puedo usar de PK
    Optional<Colaborador> colaborador = colaboradoresRepository.buscarPorIdUsuario(
        Long.valueOf(Objects.requireNonNull(context.sessionAttribute("idUsuario"))));

    Long idColaborador = colaborador.get().getUsuario().getId();

    Colaboracion donarDinero = new Colaboracion();
    Optional<Colaborador> posibleColaborador = colaboradoresRepository
        .buscarPorIdUsuario(idColaborador);
    donarDinero.setFechaColaboracion(LocalDate.now());
    donarDinero.setTipoColaboracion(TipoColaboracion.DONAR_DINERO);
    posibleColaborador.ifPresent(donarDinero::setColaborador);
    donarDinero.setDonacionDinero(donacionDinero);

    colaboracionesRepository.guardar(donarDinero);
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
