package controllers.colaboraciones;

import dtos.DonacionDineroDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import utils.ColaboracionesHelper;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarDineroController.
 */

public class DonarDineroController implements InterfaceCrudViewsHandler {

  private final ColaboracionesRepository colaboracionesRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor de la clase.
   *
   * @param colaboracionesRepository repositorio de colaboraciones.
   * @param colaboradoresRepository repositorio de colaboradores.
   * @param colaboracionesService service de colaboraciones.
   */

  public DonarDineroController(
      ColaboracionesRepository colaboracionesRepository,
      ColaboradoresRepository colaboradoresRepository,
      ColaboracionesService colaboracionesService) {
    this.colaboracionesRepository = colaboracionesRepository;
    this.colaboradoresRepository = colaboradoresRepository;
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
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "dinero");

    context.render("/colaborar.hbs", model);
  }

  @Override
  public void save(Context context) {
    DonacionDineroDto donacionDineroDto = DonacionDineroDto.fromContext(context);

    Long idUsuario = context.sessionAttribute("idUsuario");

    Optional<Colaborador> posibleColaborador = ContextHelper.getColaboradorFromContext(context);

    if (posibleColaborador.isPresent()) {
      Colaborador colaborador = posibleColaborador.get();

      Colaboracion colaboracion = this.colaboracionesService.crear(donacionDineroDto);

      ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

      context.redirect("/heladeras-solidarias");
    } else {
      context.status(500).result("Error al realizar la Donacion");
    }
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
