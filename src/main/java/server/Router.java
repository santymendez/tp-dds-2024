package server;

import config.ControllerLocator;
import controllers.CanjearPuntosController;
import controllers.CsvController2;
import controllers.HeladerasController;
import controllers.HomePageController;
import controllers.IniciarSesionController;
import controllers.MapaController;
import controllers.RegistrarUsuarioController;
import controllers.VulnerablesController;
import controllers.colaboraciones.DonarDineroController;
import controllers.colaboraciones.DonarViandasController;
import controllers.colaboraciones.TarjetasController;
import controllers.colaboraciones.ViandasController;
import io.javalin.Javalin;
import models.entities.personas.users.TipoRol;
import java.util.Map;
import java.util.Objects;

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

    // HOME PAGE
    app.get("/heladeras-solidarias", ctx -> ctx.render("/home-page.hbs",
        Map.of("titulo", "Heladeras Solidarias")));

    app.get("/heladeras-solidarias-admin",
        ControllerLocator.instanceOf(HomePageController.class)::index);

    // INICIAR SESION / REGISTRARSE
    app.get("/heladeras-solidarias/iniciar-sesion",
        ControllerLocator.instanceOf(IniciarSesionController.class)::index);

    app.post("/heladeras-solidarias/iniciar-sesion",
        ControllerLocator.instanceOf(IniciarSesionController.class)::save);

    app.get("/heladeras-solidarias/registrarse",
        ControllerLocator.instanceOf(RegistrarUsuarioController.class)::index);

    //VISTAS COLABORADOR

    //VULNERABLES

    app.get("/heladeras-solidarias/vulnerables",
        ControllerLocator.instanceOf(VulnerablesController.class)::create);

    app.post("/heladeras-solidarias/vulnerables",
        ControllerLocator.instanceOf(VulnerablesController.class)::save);

    //CANJEAR PUNTOS

    app.get("/heladeras-solidarias/canjear-puntos",
        ControllerLocator.instanceOf(CanjearPuntosController.class)::index);

    //COLABORAR
    app.get("/heladeras-solidarias/colaborar", ctx -> ctx.render("/colaborar.hbs",
        Map.of("titulo", "Heladeras")));

    app.post("/heladeras-solidarias/colaborar", ctx -> {
      String formType = ctx.formParam("formType");
      switch (formType) {
        case "donarDinero" ->
            ControllerLocator.instanceOf(DonarDineroController.class).save(ctx);
        case "donarViandas" ->
            ControllerLocator.instanceOf(DonarViandasController.class).save(ctx);
        case "realizarOfertas" ->
            ControllerLocator.instanceOf(CanjearPuntosController.class).save(ctx);
        case "colocar-heladera" ->
            ControllerLocator.instanceOf(HeladerasController.class).delete(ctx);
        case "distribuirTarjetas" ->
            ControllerLocator.instanceOf(TarjetasController.class).edit(ctx);
        case "distribuirViandas" ->
            ControllerLocator.instanceOf(ViandasController.class).edit(ctx);
        default -> ctx.status(404).render("/error404.hbs",
            Map.of("titulo", "Error 404", "mensaje", "Tipo de formulario no valido"));
      }
    });

    //VISTAS ADMINISTRADOR
    app.get("/heladeras-solidarias/heladeras-admin", ctx -> ctx.render("/heladeras-admin.hbs",
        Map.of("titulo", "Heladeras")));

    app.post("/heladeras-solidarias/heladeras-admin", ctx -> {
      String formType = ctx.formParam("formType");
      switch (Objects.requireNonNull(formType)) {

        case "darAlta" -> ControllerLocator.instanceOf(HeladerasController.class).save(ctx);

        case "darBaja" -> ControllerLocator.instanceOf(HeladerasController.class).delete(ctx);

        case "modificarHeladeras" ->
            ControllerLocator.instanceOf(HeladerasController.class).edit(ctx);

        case "verAlertas" -> System.out.println("NO LO TENEMOS");
        //CONTROLLER VER ALERTAS
        //ControllerLocator.instanceOf(AlertasController.class).index(ctx);
        default -> ctx.status(400).result("Tipo de formulario no valido");
      }
    });

    app.get("/heladeras-solidarias/ver-mapa",
        ControllerLocator.instanceOf(MapaController.class)::index);

    app.get("/heladeras-solidarias/reportar-falla", ctx -> ctx.render("/reportar-falla-tecnica.hbs",
        Map.of("titulo", "Reportar Falla")));

    app.get("/heladeras-solidarias/cargar-csv", ctx -> ctx.render("/cargar-csv.hbs",
        Map.of("titulo", "Cargar CSV")));

    app.post("heladeras-solidarias/cargar-csv",
        ControllerLocator.instanceOf(CsvController2.class)::save);

    //Query Params
    //app.get("/saludo", ctx -> {
    //ctx.result("Hola " + ctx.queryParam("nombre") + " " + ctx.queryParam("apellido"));
    //});

    //Route params | Path params
    //app.get("/saludo-para/{nombre}", ctx -> ctx.result("Hola " + ctx.pathParam("nombre")));

    //PROYECTO
    //app.get("/productos", ServiceLocator.instanceOf(ProductosController.class)::index);

    //app.get("/productos/nuevo", ServiceLocator.instanceOf(ProductosController.class)::create);

    //app.get("/productos/{id}", ServiceLocator.instanceOf(ProductosController.class)::show);

    //app.get("/productos/{id}/edicion",
    // ServiceLocator.instanceOf(ProductosController.class)::edit);

    //app.post("/productos/{id}/edicion",
    // ServiceLocator.instanceOf(ProductosController.class)::update);

    //app.post("/productos/{id}/eliminiacion",
    // ServiceLocator.instanceOf(ProductosController.class)::delete);

    //app.post("/productos", ServiceLocator.instanceOf(ProductosController.class)::save);
  }
}
