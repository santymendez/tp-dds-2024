package server.handlers;

import io.javalin.Javalin;
import java.util.Map;
import utils.helpers.ErrorHelper;

/**
 * Handler de error del servidor.
 */

public class ServerErrorHandler implements Ihandler {

  /**
   * Setea el handler de error del servidor.
   *
   * @param app la instancia de Javalin
   */

  public void setHandle(Javalin app) {
    app.exception(Exception.class, (e, context) -> {
      Map<String, String> model = ErrorHelper.generateError(
          500,
          "Error Interno del Servidor",
          "Ocurrió un error inesperado."
      );

      context.status(500);
      context.render("error-base.hbs", model);
    });
  }
}
