package config;

import controllers.HeladerasController;
import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.GenericRepository;

/**
 * Clase ServiceLocator.
 */

public class ServiceLocator {
  private static Map<String, Object> instances = new HashMap<>();


  /**
   * Obtiene la instancia de un componente.
   *
   * @param componentClass la clase del componente
   * @return la instancia del componente
   */

  @SuppressWarnings("unchecked")
  public static <T> T instanceOf(Class<T> componentClass) {
    String componentName = componentClass.getName();

    if (!instances.containsKey(componentName)) {

      if (componentName.equals(HeladerasController.class.getName())) {
        HeladerasController instance = new HeladerasController(RepositoryLocator
            .get("genericRepository", GenericRepository.class));
        instances.put(componentName, instance);
      }
    }

    return (T) instances.get(componentName);
  }
}
