package exceptions;

/**
 * Clase para lanzar una excepcion de permiso denegado.
 */

public class AccessDeniedException extends RuntimeException {

  public AccessDeniedException() {
    super("Acceso denegado");
  }

  public AccessDeniedException(String message) {
    super(message);
  }

  public AccessDeniedException(String message, Throwable cause) {
    super(message, cause);
  }

  public AccessDeniedException(Throwable cause) {
    super(cause);
  }
}
