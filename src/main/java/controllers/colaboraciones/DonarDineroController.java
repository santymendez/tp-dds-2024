package controllers.colaboraciones;

import dtos.DonacionDineroInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import services.ColaboracionesService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.metrics.TransactionStatus;

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

    context.render("colaborar.hbs", model);
  }

  @Override
  public void save(Context context) {
    DonacionDineroInputDto donacionDineroInputDto = DonacionDineroInputDto.fromContext(context);
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Colaboracion colaboracion = this.colaboracionesService.crear(donacionDineroInputDto);

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