package models.repositories;

import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.MedicionesRepository;
import models.repositories.imp.SensoresMovimientoRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.VisitasRepository;

/**
 * Service Locator.
 */
public class RepositoryLocator {

  private static final Map<String, Object> services = new HashMap<>();

  static {
    // Inicializar el mapa con las instancias de los repositorios
    services.put("heladerasRepository", new HeladerasRepository());
    services.put("colaboracionesRepository", new ColaboracionesRepository());
    services.put("colaboradoresRepository", new ColaboradoresRepository());
    services.put("incidentesRepository", new IncidentesRepository());
    services.put("sensoresMovimientoRepository", new SensoresMovimientoRepository());
    services.put("sensoresTemperaturaRepository", new SensoresTemperaturaRepository());
    services.put("tecnicosRepository", new TecnicosRepository());
    services.put("visitasRepository", new VisitasRepository());
    services.put("medicionesRepository", new MedicionesRepository());
  }

  /**
   * Obtiene el objeto repositorio a partir de una clave.
   *
   * @param key Es la clave del objeto.
   * @return El repositorio correspondiente a la clave.
   * @throws IllegalArgumentException Si no se encuentra el repositorio para la clave dada.
   */
  public static Object get(String key) {
    if (services.containsKey(key)) {
      return services.get(key);
    } else {
      throw new IllegalArgumentException("No se pudo obtener el servicio " + key);
    }
  }
}
