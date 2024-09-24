package config;

import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;

/**
 * Service Locator para obtener los servicios de repositorios.
 */
public class RepositoryLocator {

  private static final Map<String, Object> instances = new HashMap<>();

  static {
    // Inicializar el mapa con las instancias de los repositorios
    instances.put("genericRepository", new GenericRepository());
    instances.put("colaboracionesRepository", new ColaboracionesRepository());
    instances.put("colaboradoresRepository", new ColaboradoresRepository());
    instances.put("tecnicosRepository", new TecnicosRepository());
    instances.put("reportesRepository", new ReportesRepository());
    instances.put("usosTarjetasVulnerablesRepository", new UsosTarjetasVulnerablesRepository());
  }

  /**
   * Devuelve una instancia del repositorio adecuado.
   *
   * @param repositoryClass Clase del repositorio.
   * @param <T> Tipo de la clase del repositorio.
   * @return Instancia del repositorio adecuado.
   */

  public static <T> T instanceOf(Class<T> repositoryClass) {
    String repositoryClassName = repositoryClass.getName();

    if (!instances.containsKey(repositoryClassName)) {
      if (repositoryClass.equals(GenericRepository.class)) {
        instances.put(repositoryClassName, new GenericRepository());
      } else if (repositoryClass.equals(ColaboracionesRepository.class)) {
        instances.put(repositoryClassName, new ColaboracionesRepository());
      } else if (repositoryClass.equals(ColaboradoresRepository.class)) {
        instances.put(repositoryClassName, new ColaboradoresRepository());
      } else if (repositoryClass.equals(TecnicosRepository.class)) {
        instances.put(repositoryClassName, new TecnicosRepository());
      } else if (repositoryClass.equals(ReportesRepository.class)) {
        instances.put(repositoryClassName, new ReportesRepository());
      } else if (repositoryClass.equals(UsosTarjetasVulnerablesRepository.class)) {
        instances.put(repositoryClassName, new UsosTarjetasVulnerablesRepository());
      }
    }

    return (T) instances.get(repositoryClassName);
  }
}
