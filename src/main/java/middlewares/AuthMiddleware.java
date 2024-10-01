package middlewares;

import exceptions.AccessDeniedException;
import io.javalin.Javalin;
import models.entities.personas.users.TipoRol;
import io.javalin.http.Context;

/**
 * Clase Middleware para autenticacion.
 */

public class AuthMiddleware {

    public static void apply(Javalin app) {
        app.beforeMatched(ctx -> {
            var userRole = getUserRoleType(ctx);
            if (!ctx.routeRoles().isEmpty() && !ctx.routeRoles().contains(userRole)) {
                throw new AccessDeniedException();
            }
        });
    }

    private static TipoRol getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null?
                TipoRol.valueOf(context.sessionAttribute("tipo_rol")) : null;
    }
}
