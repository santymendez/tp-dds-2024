package config;

import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.imp.UsuariosRepository;

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
      } else if (repositoryClass.equals(ReportesRepository.class)) {
        instances.put(repositoryClassName, new ReportesRepository());
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
      }
    }

    return (T) instances.get(repositoryClassName);
  }
}
