package controllers.colaboraciones;

import dtos.DonacionDineroDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import services.ColaboracionesService;
import utils.ColaboracionesHelper;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**

 DonarDineroController.*/

public class DonarDineroController implements InterfaceCrudViewsHandler {

  private final ColaboracionesService colaboracionesService;

  public DonarDineroController(ColaboracionesService colaboracionesService) {
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
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Colaboracion colaboracion = this.colaboracionesService.crear(donacionDineroDto);

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

    //Opcion 1 ponerlo en la sesión
    //context.sessionAttribute("success", true);

    //Opcion 2 ponerlo en un query param
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