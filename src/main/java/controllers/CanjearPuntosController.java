package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import services.CanjearPuntosService;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para canjear los puntos.
 */

public class CanjearPuntosController implements InterfaceCrudViewsHandler {

  private final GenericRepository ofertasRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  private final CanjearPuntosService canjearPuntosService;

  /**
   * Constructor del controller de canjear puntos.
   *
   * @param ofertasRepository       el repositorio de ofertas.
   * @param colaboradoresRepository el repositorio de colaboradores.
   * @param canjearPuntosService    el servicio de canjear puntos.
   */

  public CanjearPuntosController(
      GenericRepository ofertasRepository,
      ColaboradoresRepository colaboradoresRepository,
      CanjearPuntosService canjearPuntosService
  ) {
    this.ofertasRepository = ofertasRepository;
    this.colaboradoresRepository = colaboradoresRepository;
    this.canjearPuntosService = canjearPuntosService;
  }

  /**
   * Muestra las ofertas.
   *
   * @param context el contexto de la aplicación.
   */

  public void index(Context context) {
    List<Oferta> ofertas = ofertasRepository.buscarTodos(Oferta.class);

    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Ofertas");
    model.put("ofertas", ofertas);

    Long idSesion = context.sessionAttribute("idUsuario");
    if (idSesion != null) {
      model.put("activeSession", true);
      model.put("tipoRol", context.sessionAttribute("tipoRol"));

      colaboradoresRepository.buscarPorIdUsuario(idSesion).ifPresent(colaborador -> {
        model.put("puntos", colaborador.getReconocimiento().getPuntosPorColaborar());
      });

    } else {
      model.put("activeSession", false);
      context.redirect("/heladeras-solidarias");
      return;
    }

    context.render("canjear-puntos.hbs", model);
  }

  public void show(Context context) {

  }

  public void create(Context context) {
  }

  public void save(Context context) {

  }

  public void edit(Context context) {

  }

  /**
   * metodo para canjear una oferta por puntos.
   *
   * @param context el contexto de quien realiza el post.
   */

  public void update(Context context) {
    Long idUsuario = context.sessionAttribute("idUsuario");
    Long idOferta = Long.valueOf(Objects.requireNonNull(context.formParam("idOferta")));

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();
    Oferta oferta = this.ofertasRepository.buscarPorId(idOferta, Oferta.class).get();

    if (colaborador.puedeCanjear(oferta)) {
      this.canjearPuntosService.crear(oferta, colaborador);
      context.redirect("/heladeras-solidarias");
    } else {
      //TODO MANEJAR ERROR PUNTOS INSUFICIENTES
      context.redirect("/heladeras-solidarias/canjear-puntos");
    }
  }

  public void delete(Context context) {

  }
}
