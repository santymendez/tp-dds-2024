package middlewares;

import exceptions.AccessDeniedException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import models.entities.personas.users.TipoRol;

/**
 * Clase Middleware para autenticacion.
 */

public class AuthMiddleware {

  /**
   * Aplica el middleware de autenticacion.
   *
   * @param app la instancia de Javalin
   */

  public static void apply(Javalin app) {
    app.beforeMatched(ctx -> {
      var userRole = getUserRoleType(ctx);

      if (!ctx.routeRoles().isEmpty() && !ctx.routeRoles().contains(userRole)) {
        throw new AccessDeniedException();
      }
    });
  }

  private static TipoRol getUserRoleType(Context context) {
    return context.sessionAttribute("tipoRol") != null
        ? TipoRol.valueOf(context.sessionAttribute("tipoRol")) : null;
  }
}
