package config;

import java.util.HashMap;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.ModelosRepository;
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
import services.ModelosService;
import services.OfertasService;
import services.ReportesHeladerasService;
import services.SuscripcionesService;
import services.TarjetaColaboradorService;
import services.TarjetasVulnerablesService;
import services.UsuariosService;
import services.VisitasTecnicasService;
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
            RepositoryLocator.instanceOf(ColaboradoresRepository.class),
            RepositoryLocator.instanceOf(UsuariosRepository.class)
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
            RepositoryLocator.instanceOf(VulnerablesRepository.class)
        );
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(ColaboracionesService.class)) {
        ColaboracionesService instance = new ColaboracionesService();
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(OfertasService.class)) {
        OfertasService instance = new OfertasService();
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(UsuariosService.class)) {
        UsuariosService instance = new UsuariosService();
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(IncidentesService.class)) {
        IncidentesService instance = new IncidentesService(
            RepositoryLocator.instanceOf(GenericRepository.class),
            RepositoryLocator.instanceOf(IncidentesRepository.class)
        );
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

      } else if (serviceClass.equals(SuscripcionesService.class)) {
        SuscripcionesService instance = new SuscripcionesService();
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(ModelosService.class)) {
        ModelosService instance = new ModelosService(
            RepositoryLocator.instanceOf(ModelosRepository.class)
        );
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(VisitasTecnicasService.class)) {
        VisitasTecnicasService instance = new VisitasTecnicasService(
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(ReportesHeladerasService.class)) {
        ReportesHeladerasService instance = new ReportesHeladerasService(
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);

      } else if (serviceClass.equals(TarjetasVulnerablesService.class)) {
        TarjetasVulnerablesService instance = new TarjetasVulnerablesService(
            RepositoryLocator.instanceOf(TarjetasVulnerablesRepository.class),
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(serviceName, instance);

      }
    }
    return (T) instances.get(serviceName);
  }
}