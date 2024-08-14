package models.repositories;

import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.SensoresMovimientoRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.VisitasRepository;

/**
 * Service Locator.
 */

//TODO
public class RepositoryLocator {
  private static final Map<String, Object> services = new HashMap<>();

  private static void add(String key, Object repository) {
    services.put(key, repository);
  }

  /**
   * Obtiene el objeto repositorio, a partir de una key.
   *
   * @param key Es la key del objeto.
   */

  public static Object get(String key) {
    if (services.containsKey(key)) {
      return services.get(key);
    }

    switch (key) {
      case "heladerasRepository" -> {
        HeladerasRepository heladerasRepository = new HeladerasRepository();
        add(key, heladerasRepository);
        return heladerasRepository;
      }
      case "colaboracionesRepository" -> {
        ColaboracionesRepository colaboracionesRepository = new ColaboracionesRepository();
        add(key, colaboracionesRepository);
        return colaboracionesRepository;
      }
      case "colaboradoresRepository" -> {
        ColaboradoresRepository colaboradoresRepository = new ColaboradoresRepository();
        add(key, colaboradoresRepository);
        return colaboradoresRepository;
      }
      case "incidentesRepository" -> {
        IncidentesRepository incidentesRepository = new IncidentesRepository();
        add(key, incidentesRepository);
        return incidentesRepository;
      }
      case "sensoresMovimientoRepository" -> {
        SensoresMovimientoRepository sensoresMovimientoRepository
            = new SensoresMovimientoRepository();
        add(key, sensoresMovimientoRepository);
        return sensoresMovimientoRepository;
      }
      case "sensoresTemperaturaRepository" -> {
        SensoresTemperaturaRepository sensoresTemperaturaRepository
            = new SensoresTemperaturaRepository();
        add(key, sensoresTemperaturaRepository);
        return sensoresTemperaturaRepository;
      }
      case "tecnicosRepository" -> {
        TecnicosRepository tecnicosRepository = new TecnicosRepository();
        add(key, tecnicosRepository);
        return tecnicosRepository;
      }
      case "visitasRepository" -> {
        VisitasRepository visitasRepository = new VisitasRepository();
        add(key, visitasRepository);
        return visitasRepository;
      }
      default -> throw new IllegalArgumentException("No se ha podido obtener el servicio " + key);
    }
  }
}
