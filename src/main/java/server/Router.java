package server;

import config.ControllerLocator;
import controllers.CanjearPuntosController;
import controllers.ColaboracionesController;
import controllers.CsvController;
import controllers.HeladerasController;
import controllers.HomePageController;
import controllers.IncidentesController;
import controllers.IniciarSesionController;
import controllers.MapaController;
import controllers.RegistrarColaboradorController;
import controllers.SuscribirseController;
import controllers.VulnerablesController;
import controllers.colaboraciones.DistribuirViandasController;
import controllers.colaboraciones.DonarDineroController;
import controllers.colaboraciones.DonarViandasController;
import controllers.colaboraciones.DsitribuirTarjetasController;
import controllers.colaboraciones.RealizarOfertasController;
import io.javalin.Javalin;
import java.util.Map;
import java.util.Objects;
import models.entities.personas.users.TipoRol;

/**
 * Clase Router.
 */

public class Router {

  /**
   * Inicializa las rutas de la aplicación.
   *
   * @param app la instancia de Javalin
   */

  public static void init(Javalin app) {

    //HOME PAGE
    app.get("/heladeras-solidarias",
        ControllerLocator.instanceOf(HomePageController.class)::index);

    // INICIAR SESION / REGISTRARSE
    app.get("/heladeras-solidarias/iniciar-sesion",
        ControllerLocator.instanceOf(IniciarSesionController.class)::create);

    app.post("/heladeras-solidarias/iniciar-sesion",
        ControllerLocator.instanceOf(IniciarSesionController.class)::save);

    app.get("/heladeras-solidarias/registrarse",
            ControllerLocator.instanceOf(RegistrarColaboradorController.class)::create);

    app.post("/heladeras-solidarias/registrarse",
        ControllerLocator.instanceOf(RegistrarColaboradorController.class)::save);

    app.get("/heladeras-solidarias/cerrar-sesion",
            ControllerLocator.instanceOf(IniciarSesionController.class)::update);

    //VISTAS COLABORADOR

    //VULNERABLES

    app.get("/heladeras-solidarias/vulnerables",
        ControllerLocator.instanceOf(VulnerablesController.class)::create,
        TipoRol.PERSONA_FISICA, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/vulnerables",
        ControllerLocator.instanceOf(VulnerablesController.class)::save,
        TipoRol.PERSONA_FISICA);

    //CANJEAR PUNTOS

    app.get("/heladeras-solidarias/canjear-puntos",
        ControllerLocator.instanceOf(CanjearPuntosController.class)::index,
        TipoRol.PERSONA_FISICA, TipoRol.EMPRESA_ASOCIADA, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/canjear-puntos",
        ControllerLocator.instanceOf(CanjearPuntosController.class)::update,
        TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA);

    //COLABORAR
    app.get("/heladeras-solidarias/colaborar", 
        ControllerLocator.instanceOf(ColaboracionesController.class)::create,
        TipoRol.PERSONA_FISICA,
        TipoRol.PERSONA_JURIDICA,
        TipoRol.ADMINISTRADOR,
        TipoRol.EMPRESA_ASOCIADA);

    app.post("/heladeras-solidarias/colaborar", ctx -> {
      String formType = ctx.formParam("formType");
      switch (formType) {
        case "donarDinero" ->
            ControllerLocator.instanceOf(DonarDineroController.class).save(ctx);
        case "donarViandas" ->
            ControllerLocator.instanceOf(DonarViandasController.class).save(ctx);
        case "realizarOfertas" ->
            ControllerLocator.instanceOf(RealizarOfertasController.class).save(ctx);
        case "colocar-heladera" ->
            ControllerLocator.instanceOf(HeladerasController.class).save(ctx);
        case "distribuirTarjetas" ->
            ControllerLocator.instanceOf(DsitribuirTarjetasController.class).edit(ctx);
        case "distribuirViandas" ->
            ControllerLocator.instanceOf(DistribuirViandasController.class).edit(ctx);
        default -> ctx.status(404).render("/error404.hbs",
            Map.of("titulo", "Error 404", "mensaje", "Tipo de formulario no valido"));
      }
    }, TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA);

    app.get("/heladeras-solidarias/heladeras",
        ControllerLocator.instanceOf(HeladerasController.class)::index,
        TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/heladeras", ctx -> {
      String formType = ctx.formParam("formType");
      switch (Objects.requireNonNull(formType)) {
        case "suscribirse" -> ControllerLocator.instanceOf(SuscribirseController.class).save(ctx);
        case "reportarFalla" -> ControllerLocator.instanceOf(IncidentesController.class).save(ctx);
        case "recomendaciones" -> System.out.println("NO LO TENEMOS");
        default -> ctx.status(400).result("Tipo de formulario no valido");
      }
    }, TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA, TipoRol.ADMINISTRADOR);

    //VISTAS ADMINISTRADOR
    app.get("/heladeras-solidarias/heladeras-admin",
        ControllerLocator.instanceOf(HeladerasController.class)::index, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/heladeras-admin", ctx -> {
      String formType = ctx.formParam("formType");
      switch (Objects.requireNonNull(formType)) {
        case "darAlta" -> ControllerLocator.instanceOf(HeladerasController.class).save(ctx);
        case "darBaja" -> ControllerLocator.instanceOf(HeladerasController.class).delete(ctx);
        case "modificar" ->
            ControllerLocator.instanceOf(HeladerasController.class).update(ctx);
        case "verAlertas" -> System.out.println("NO LO TENEMOS");
        default -> ctx.status(400).result("Tipo de formulario no valido");
      }
    }, TipoRol.ADMINISTRADOR);

    app.get("/heladeras-solidarias/ver-mapa",
        ControllerLocator.instanceOf(MapaController.class)::index);

    app.get("/heladeras-solidarias/cargar-csv",
        ControllerLocator.instanceOf(CsvController.class)::create, TipoRol.ADMINISTRADOR);

    app.post("heladeras-solidarias/cargar-csv",
        ControllerLocator.instanceOf(CsvController.class)::save, TipoRol.ADMINISTRADOR);

  }
}
