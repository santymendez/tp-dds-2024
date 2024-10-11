package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Helper para los errores.
 */

public class ErrorHelper {

  /**
   * Genera un Map con los parametros del error.
   *
   * @param errorCode el codigo de error.
   * @param name el nombre del error.
   * @param description la descripcion del error.
   * @return un map del error.
   */

  public static Map<String, String> generateError(
      Integer errorCode, String name, String description
  ) {
    Map<String, String> map = new HashMap<>();
    map.put("errorCode", Integer.toString(errorCode));
    map.put("name", name);
    map.put("description", description);

    return map;
  }
}
