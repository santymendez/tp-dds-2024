package server.handlers;

import io.javalin.Javalin;

/**
 * Interfaz para los Handlers.
 */

public interface IHandler {
    void setHandle(Javalin app);
}
