package models.repositories;

import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.BarriosRepository;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.FormulariosRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.MedicionesRepository;
import models.repositories.imp.RegistrosVulnerablesRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.SensoresMovimientoRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.imp.VisitasRepository;
import models.repositories.imp.VulnerablesRepository;

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
    services.put("reportesRepository", new ReportesRepository());
    services.put("tarjetasVulnerablesRepository", new TarjetasVulnerablesRepository());
    services.put("vulnerablesRepository", new VulnerablesRepository());
    services.put("usosTarjetasVulnerablesRepository", new UsosTarjetasVulnerablesRepository());
    services.put("barriosRepository", new BarriosRepository());
    services.put("direccionesRepository", new DireccionesRepository());
    services.put("registrosVulnerablesRepository", new RegistrosVulnerablesRepository());
    services.put("formulariosRepository", new FormulariosRepository());
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
