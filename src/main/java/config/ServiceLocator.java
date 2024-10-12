package config;

import java.util.HashMap;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import models.repositories.imp.UsuariosRepository;
import models.repositories.imp.VulnerablesRepository;
import services.CanjearPuntosService;
import services.ColaboracionesService;
import services.ColaboradoresService;
import services.DireccionesService;
import services.HeladerasService;
import services.IncidentesService;
import services.OfertasService;
import services.TarjetaColaboradorService;
import services.UsuariosService;
import services.VulnerablesService;

/**
 * Clase serviceLocator para obtener los services.
 */

public class ServiceLocator {
  private static final HashMap<String, Object> instances = new HashMap<>();

  /**
   * Obtiene la instancia de un Service.
   *
   * @param serviceClass la clase del Service.
   * @param <T>          el tipo de la clase del Service.
   * @return la instancia del Service.
   */

  @SuppressWarnings("unchecked")
  public static <T> T instanceOf(Class<T> serviceClass) {
    String serviceName = serviceClass.getName();

    if (!instances.containsKey(serviceName)) {
      if (serviceClass.equals(ColaboradoresService.class)) {
        ColaboradoresService instance = new ColaboradoresService(
            RepositoryLocator.instanceOf(UsuariosRepository.class),
            RepositoryLocator.instanceOf(ColaboradoresRepository.class)
        );
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(DireccionesService.class)) {
        DireccionesService instance = new DireccionesService(
            RepositoryLocator.instanceOf(DireccionesRepository.class),
            RepositoryLocator.instanceOf(ProvinciasRepository.class)
        );
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(VulnerablesService.class)) {
        VulnerablesService instance = new VulnerablesService(
            RepositoryLocator.instanceOf(GenericRepository.class),
            RepositoryLocator.instanceOf(TarjetasVulnerablesRepository.class),
            RepositoryLocator.instanceOf(VulnerablesRepository.class)
        );
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(ColaboracionesService.class)) {
        ColaboracionesService instance = new ColaboracionesService(
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(OfertasService.class)) {
        OfertasService instance = new OfertasService();
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(UsuariosService.class)) {
        UsuariosService instance = new UsuariosService();
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(IncidentesService.class)) {
        IncidentesService instance = new IncidentesService(
            RepositoryLocator.instanceOf(GenericRepository.class));
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(HeladerasService.class)) {
        HeladerasService instance = new HeladerasService(
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(CanjearPuntosService.class)) {
        CanjearPuntosService instance = new CanjearPuntosService(
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);
      } else if (serviceClass.equals(TarjetaColaboradorService.class)) {
        TarjetaColaboradorService instance = new TarjetaColaboradorService(
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);
      }
    }
    return (T) instances.get(serviceName);
  }
}