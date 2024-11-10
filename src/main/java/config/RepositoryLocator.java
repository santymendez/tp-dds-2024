package config;

import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.CanjesRepository;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.ModelosRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.repositories.imp.ReportesSemanalesRepository;
import models.repositories.imp.SensoresMovimientoRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.imp.TarjetasColaboradoresRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.imp.UsuariosRepository;
import models.repositories.imp.VulnerablesRepository;

/**
 * Service Locator para obtener los servicios de repositorios.
 */
public class RepositoryLocator {

  private static final Map<String, Object> instances = new HashMap<>();

  /**
   * Devuelve una instancia del repositorio adecuado.
   *
   * @param repositoryClass Clase del repositorio.
   * @param <T> Tipo de la clase del repositorio.
   * @return Instancia del repositorio adecuado.
   */

  @SuppressWarnings("unchecked")
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
      } else if (repositoryClass.equals(ReportesHeladerasRepository.class)) {
        instances.put(repositoryClassName, new ReportesHeladerasRepository());
      } else if (repositoryClass.equals(UsosTarjetasVulnerablesRepository.class)) {
        instances.put(repositoryClassName, new UsosTarjetasVulnerablesRepository());
      } else if (repositoryClass.equals(DireccionesRepository.class)) {
        instances.put(repositoryClassName, new DireccionesRepository());
      } else if (repositoryClass.equals(ProvinciasRepository.class)) {
        instances.put(repositoryClassName, new ProvinciasRepository());
      } else if (repositoryClass.equals(UsuariosRepository.class)) {
        instances.put(repositoryClassName, new UsuariosRepository());
      } else if (repositoryClass.equals(TarjetasVulnerablesRepository.class)) {
        instances.put(repositoryClassName, new TarjetasVulnerablesRepository());
      } else if (repositoryClass.equals(TarjetasColaboradoresRepository.class)) {
        instances.put(repositoryClassName, new TarjetasColaboradoresRepository());
      } else if (repositoryClass.equals(VulnerablesRepository.class)) {
        instances.put(repositoryClassName, new VulnerablesRepository());
      } else if (repositoryClass.equals(HeladerasRepository.class)) {
        instances.put(repositoryClassName, new HeladerasRepository());
      } else if (repositoryClass.equals(IncidentesRepository.class)) {
        instances.put(repositoryClassName, new IncidentesRepository());
      } else if (repositoryClass.equals(ReportesSemanalesRepository.class)) {
        instances.put(repositoryClassName, new ReportesSemanalesRepository());
      } else if (repositoryClass.equals(ModelosRepository.class)) {
        instances.put(repositoryClassName, new ModelosRepository());
      } else if (repositoryClass.equals(SensoresTemperaturaRepository.class)) {
        instances.put(repositoryClassName, new SensoresTemperaturaRepository());
      } else if (repositoryClass.equals(SensoresMovimientoRepository.class)) {
        instances.put(repositoryClassName, new SensoresMovimientoRepository());
      } else if (repositoryClass.equals(CanjesRepository.class)) {
        instances.put(repositoryClassName, new CanjesRepository());
      }
    }

    return (T) instances.get(repositoryClassName);
  }
}
