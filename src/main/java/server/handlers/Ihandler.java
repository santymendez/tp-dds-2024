package server.handlers;

import io.javalin.Javalin;

/**
 * Interfaz para los Handlers.
 */

public interface Ihandler {
  void setHandle(Javalin app);
}
