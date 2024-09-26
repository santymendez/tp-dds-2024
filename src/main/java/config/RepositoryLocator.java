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
 * Service Locator.
 */
public class RepositoryLocator {

  private static final Map<String, Object> services = new HashMap<>();

  static {
    // Inicializar el mapa con las instancias de los repositorios
    services.put("genericRepository", new GenericRepository());
    services.put("colaboracionesRepository", new ColaboracionesRepository());
    services.put("colaboradoresRepository", new ColaboradoresRepository());
    services.put("tecnicosRepository", new TecnicosRepository());
    services.put("reportesRepository", new ReportesRepository());
    services.put("usosTarjetasVulnerablesRepository", new UsosTarjetasVulnerablesRepository());
  }

  /**
   * Obtiene el objeto repositorio a partir de una clave.
   *
   * @param key Es la clave del objeto.
   * @return El repositorio correspondiente a la clave.
   * @throws IllegalArgumentException Si no se encuentra el repositorio para la clave dada.
   */

  @SuppressWarnings("unchecked")
  public static <T> T get(String key, Class<T> tipoClase) {
    Object service = services.get(key);
    if (service == null) {
      throw new IllegalArgumentException("No se pudo obtener el servicio " + key);
    }
    if (!tipoClase.isInstance(service)) {
      throw new IllegalArgumentException("El servicio " + key + " no es del tipo "
              + tipoClase.getName());
    }
    return (T) service;
  }
}
