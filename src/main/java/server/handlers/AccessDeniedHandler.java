package server.handlers;

import exceptions.AccessDeniedException;
import io.javalin.Javalin;
import java.util.Map;
import utils.helpers.ErrorHelper;

/**
 * Handler de acceso denegado.
 */

public class AccessDeniedHandler implements Ihandler {

  /**
   * Setea el handler de acceso denegado.
   *
   * @param app la instancia de Javalin
   */

  public void setHandle(Javalin app) {
    app.exception(AccessDeniedException.class, (e, context) -> {
      Map<String, String> model = ErrorHelper.generateError(
          403,
          "Acceso Denegado",
          "No posee permisos para acceder a esta página"
      );

      context.status(403);
      context.render("/error-base.hbs", model);
    });
  }
}
