package utils;

import io.javalin.http.Context;
import java.util.Objects;

/**
 * Clase Auxiliar para chequear si el parametro de un formulario es vacio.
 */

public class ContextHelper {

  public static Boolean isEmpty(Context context, String key) {
    return Objects.equals(context.formParam(key), "");
  }

  /**
   * Chequea si los valores de un formulario son vacios.
   *
   * @param context el contexto de la aplicación.
   * @param keys los valores a chequear.
   * @return true si alguno de los valores es vacio, false en caso contrario.
   */

  public static Boolean areEmpty(Context context, String... keys) {
    for (String value : keys) {
      if (isEmpty(context, value)) {
        return true;
      }
    }
    return false;
  }
}
