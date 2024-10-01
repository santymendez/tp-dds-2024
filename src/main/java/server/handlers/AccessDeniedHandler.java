package server.handlers;

import exceptions.AccessDeniedException;
import io.javalin.Javalin;

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
      context.status(401);
      context.render("404.hbs"); //TODO adaptar para todos los errores
    });
  }
}
