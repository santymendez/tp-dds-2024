package config;

import controllers.CanjearPuntosController;
import controllers.CsvController;
import controllers.CsvController2;
import controllers.HeladerasController;
import controllers.MapaController;
import controllers.VulnerablesController;
import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import services.ColaboracionesService;
import services.ColaboradoresService;
import services.OfertasService;
import services.VulnerablesService;
import utils.sender.channels.EmailSender;

/**
 * Clase ServiceLocator para obtener los Controllers.
 */

public class ControllerLocator {
  private static final Map<String, Object> instances = new HashMap<>();

  /**
   * Obtiene la instancia de un componente.
   *
   * @param controllerClass la clase del componente
   * @return la instancia del componente
   */

  @SuppressWarnings("unchecked")
  public static <T> T instanceOf(Class<T> controllerClass) {
    String controllerName = controllerClass.getName();

    if (!instances.containsKey(controllerName)) {
      if (controllerClass.equals(HeladerasController.class)) {
        HeladerasController instance = new HeladerasController(RepositoryLocator
            .instanceOf(GenericRepository.class)
        );
        instances.put(controllerName, instance);
      } else if (controllerClass.equals(CsvController.class)) {
        CsvController instance = new CsvController(
            ServiceLocator.instanceOf(ColaboradoresService.class),
            RepositoryLocator.instanceOf(ColaboradoresRepository.class),
            RepositoryLocator.instanceOf(ColaboracionesRepository.class),
            EmailSender.getInstance()
        );
        instances.put(controllerName, instance);
      } else if (controllerClass.equals(CanjearPuntosController.class)) {
        CanjearPuntosController instance = new CanjearPuntosController(
                RepositoryLocator.instanceOf(GenericRepository.class),
                ServiceLocator.instanceOf(OfertasService.class)
        );
        instances.put(controllerName, instance);
      } else if (controllerClass.equals(VulnerablesController.class)) {
        VulnerablesController instance = new VulnerablesController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(VulnerablesService.class)
        );
        instances.put(controllerName, instance);
      } else if (controllerClass.equals(MapaController.class)) {
        MapaController instance =
            new MapaController(RepositoryLocator.instanceOf(GenericRepository.class));
        instances.put(controllerName, instance);
      } else if (controllerClass.equals(CsvController2.class)) {
        CsvController2 instance = new CsvController2(
            ServiceLocator.instanceOf(ColaboradoresService.class),
            RepositoryLocator.instanceOf(ColaboradoresRepository.class),
            RepositoryLocator.instanceOf(ColaboracionesRepository.class),
            ServiceLocator.instanceOf(ColaboracionesService.class),
            EmailSender.getInstance()
        );
        instances.put(controllerName, instance);
      }
    }

    return (T) instances.get(controllerName);
  }
}
