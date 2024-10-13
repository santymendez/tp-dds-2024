package config;

import controllers.AgregarDireccionController;
import controllers.CanjearPuntosController;
import controllers.ColaboracionesController;
import controllers.CsvController;
import controllers.HeladerasAdminController;
import controllers.HeladerasController;
import controllers.HomePageController;
import controllers.IniciarSesionController;
import controllers.MapaController;
import controllers.OldCsvController;
import controllers.RecomendacionesController;
import controllers.RegistrarColaboradorController;
import controllers.ReportarFallaTecnicaController;
import controllers.ReportesController;
import controllers.SuscribirseController;
import controllers.VulnerablesController;
import controllers.colaboraciones.DistribuirViandasController;
import controllers.colaboraciones.DonarDineroController;
import controllers.colaboraciones.DonarViandasController;
import controllers.colaboraciones.HacerseCargoController;
import controllers.colaboraciones.RealizarOfertasController;
import java.util.HashMap;
import java.util.Map;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.repositories.imp.ReportesSemanalesRepository;
import models.repositories.imp.TarjetasColaboradoresRepository;
import models.repositories.imp.UsuariosRepository;
import models.searchers.BuscadorTecnicosCercanos;
import services.CanjearPuntosService;
import services.ColaboracionesService;
import services.ColaboradoresService;
import services.DireccionesService;
import services.HeladerasService;
import services.IncidentesService;
import services.OfertasService;
import services.SuscripcionesService;
import services.TarjetaColaboradorService;
import services.UsuariosService;
import services.VulnerablesService;
import utils.recomendator.adapter.AdapterServicioRecomendacion;
import utils.security.Autenticador;
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
        HeladerasController instance = new HeladerasController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            RepositoryLocator.instanceOf(IncidentesRepository.class),
            ServiceLocator.instanceOf(HeladerasService.class),
            ServiceLocator.instanceOf(DireccionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(OldCsvController.class)) {
        OldCsvController instance = new OldCsvController(
            ServiceLocator.instanceOf(ColaboradoresService.class),
            EmailSender.getInstance(),
            ServiceLocator.instanceOf(ColaboracionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(CanjearPuntosController.class)) {
        CanjearPuntosController instance = new CanjearPuntosController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            RepositoryLocator.instanceOf(ColaboradoresRepository.class),
            ServiceLocator.instanceOf(CanjearPuntosService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(VulnerablesController.class)) {
        VulnerablesController instance = new VulnerablesController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(VulnerablesService.class),
            ServiceLocator.instanceOf(DireccionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(MapaController.class)) {
        MapaController instance =
            new MapaController(RepositoryLocator.instanceOf(GenericRepository.class));
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(CsvController.class)) {
        CsvController instance = new CsvController(
            ServiceLocator.instanceOf(ColaboradoresService.class),
            ServiceLocator.instanceOf(ColaboracionesService.class),
            EmailSender.getInstance()
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(IniciarSesionController.class)) {
        IniciarSesionController instance = new IniciarSesionController(
            RepositoryLocator.instanceOf(UsuariosRepository.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(HomePageController.class)) {
        HomePageController instance = new HomePageController();
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(RegistrarColaboradorController.class)) {
        RegistrarColaboradorController instance = new RegistrarColaboradorController(
            RepositoryLocator.instanceOf(UsuariosRepository.class),
            RepositoryLocator.instanceOf(GenericRepository.class),
            RepositoryLocator.instanceOf(ProvinciasRepository.class),
            ServiceLocator.instanceOf(UsuariosService.class),
            ServiceLocator.instanceOf(ColaboradoresService.class),
            ServiceLocator.instanceOf(DireccionesService.class),
            ServiceLocator.instanceOf(TarjetaColaboradorService.class),
            UtilsLocator.instanceOf(Autenticador.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(ReportarFallaTecnicaController.class)) {
        ReportarFallaTecnicaController instance = new ReportarFallaTecnicaController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(IncidentesService.class),
            UtilsLocator.instanceOf(BuscadorTecnicosCercanos.class),
            RepositoryLocator.instanceOf(ReportesHeladerasRepository.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(SuscribirseController.class)) {
        SuscribirseController instance = new SuscribirseController(
            RepositoryLocator.instanceOf(HeladerasRepository.class),
            ServiceLocator.instanceOf(SuscripcionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(HeladerasAdminController.class)) {
        HeladerasAdminController instance = new HeladerasAdminController();
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(ColaboracionesController.class)) {
        ColaboracionesController instance = new ColaboracionesController(
            RepositoryLocator.instanceOf(HeladerasRepository.class),
            RepositoryLocator.instanceOf(GenericRepository.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(DonarDineroController.class)) {
        DonarDineroController instance = new DonarDineroController(
            ServiceLocator.instanceOf(ColaboracionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(DistribuirViandasController.class)) {
        DistribuirViandasController instance = new DistribuirViandasController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(ColaboracionesService.class),
            RepositoryLocator.instanceOf(TarjetasColaboradoresRepository.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(DonarViandasController.class)) {
        DonarViandasController instance = new DonarViandasController(
            RepositoryLocator.instanceOf(TarjetasColaboradoresRepository.class),
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(ColaboracionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(RealizarOfertasController.class)) {
        RealizarOfertasController instance = new RealizarOfertasController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(OfertasService.class),
            ServiceLocator.instanceOf(ColaboracionesService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(AgregarDireccionController.class)) {
        AgregarDireccionController instance = new AgregarDireccionController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(DireccionesService.class),
            ServiceLocator.instanceOf(TarjetaColaboradorService.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(ReportesController.class)) {
        ReportesController instance = new ReportesController(
            RepositoryLocator.instanceOf(GenericRepository.class),
                RepositoryLocator.instanceOf(ReportesSemanalesRepository.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(RecomendacionesController.class)) {
        RecomendacionesController instance = new RecomendacionesController(
            UtilsLocator.instanceOf(AdapterServicioRecomendacion.class)
        );
        instances.put(controllerName, instance);

      } else if (controllerClass.equals(HacerseCargoController.class)) {
        HacerseCargoController instance = new HacerseCargoController(
            RepositoryLocator.instanceOf(GenericRepository.class),
            ServiceLocator.instanceOf(DireccionesService.class),
            ServiceLocator.instanceOf(ColaboracionesService.class)
        );
        instances.put(controllerName, instance);

      }
    }
    return (T) instances.get(controllerName);
  }
}
