package server;

import config.ControllerLocator;
import controllers.CanjearPuntosController;
import controllers.HeladerasController;
import io.javalin.Javalin;
import java.util.Map;

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

    app.get("/heladerasSolidarias", ctx -> ctx.render("/home-page.hbs",
        Map.of("titulo", "Heladeras Solidarias")));

    app.get("/heladerasSolidarias/iniciarSesion", ctx -> ctx.render("/iniciar-sesion.hbs",
        Map.of("titulo", "Iniciar Sesion")));

    app.get("/heladerasSolidarias/registrarse", ctx -> ctx.render("/registrarse.hbs",
        Map.of("titulo", "Registrarse")));

    app.get("/heladerasSolidarias/colaborar", ctx -> ctx.render("/colaborar.hbs",
        Map.of("titulo", "Colaborar")));

    app.get("/heladerasSolidarias/heladeras", ctx -> ctx.render("/heladeras-colaborador.hbs",
        Map.of("titulo", "Heladeras")));

    app.get("/heladerasSolidarias/heladeras/verMapa",
        ControllerLocator.instanceOf(HeladerasController.class)::index);

    app.get("/heladerasSolidarias/reportes", ctx -> ctx.render("/reportes.hbs"));

    app.get("/heladerasSolidarias/puntos",
        ControllerLocator.instanceOf(CanjearPuntosController.class)::index);

    app.get("/heladerasSolidarias/vulnerables", ctx -> ctx.render("/registrar-vulnerable.hbs"));

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
