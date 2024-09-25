package server;

import io.javalin.Javalin;

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
    //EJEMPLOS
    app.get("/heladerassolidarias", ctx -> ctx
        .result("Se viene el fin de semana todo a la cancha vamo a ir!"));

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
