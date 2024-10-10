package controllers.colaboraciones;

import io.javalin.http.Context;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.repositories.imp.TarjetasColaboradoresRepository;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarViandasController.
 */

public class DonarViandasController implements InterfaceCrudViewsHandler {
  private final TarjetasColaboradoresRepository tarjetasColaboradoresRepository;

  public DonarViandasController(TarjetasColaboradoresRepository tarjetasColaboradoresRepository) {
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

    if (posibleTarjeta.isPresent()) {
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

    //TODO la colaboracion en si misma


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
