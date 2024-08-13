package utils;

import java.util.HashMap;
import java.util.Map;
import models.repositories.ColaboracionesRepository;
import models.repositories.heladera.HeladerasRepository;

/**
 * Service Locator.
 */

//TODO
public class RepositoryLocator {
  private static final Map<String, Object> services = new HashMap<>();

  /**
   * Obtiene el objeto del Service Locator.
   *
   * @param name el nombre de la key.
   * @return el objeto en cuestion.
   */

  public static Object get(String name) {
    if (services.containsKey(name)) {
      return services.get(name);
    }

    switch (name) {
      case "heladerasRepository" -> {
        HeladerasRepository heladerasRepository = new HeladerasRepository();
        services.put(name, heladerasRepository);
        return heladerasRepository;
      }
      case "colaboracionesRepository" -> {
        ColaboracionesRepository colaboracionesRepository = new ColaboracionesRepository();
        services.put(name, colaboracionesRepository);
        return colaboracionesRepository;
      }
      default -> {
        return null;
      }
    }
  }
}
