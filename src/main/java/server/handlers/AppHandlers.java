package server.handlers;

import io.javalin.Javalin;
import java.util.Arrays;

/**
 * Clase AppHandlers.
 */

public class AppHandlers {
  private final Ihandler[] handlers = new Ihandler[]{
      new AccessDeniedHandler(),
      new ServerErrorHandler()
  };

  public static void applyHandlers(Javalin app) {
    Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
  }
}
