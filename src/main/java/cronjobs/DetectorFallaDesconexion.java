package cronjobs;

import config.RepositoryLocator;
import config.ServiceLocator;
import config.UtilsLocator;
import java.util.List;
import models.db.PersistenceUnitSwitcher;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.SensorTemperatura;
import models.entities.personas.colaborador.suscripcion.TipoSuscripcion;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.searchers.BuscadorTecnicosCercanos;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import services.IncidentesService;

/**
 * Representa al detector de la falla de conexion entre la heladera
 * y el sensor de temperatura.
 */

public class DetectorFallaDesconexion implements Job {

  /**
   * Main para el CronJob encargado de la revision de la conexion.
   */

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    PersistenceUnitSwitcher.switchPersistenceUnit("cronjob-persistence-unit");

    GenericRepository genericRepository = RepositoryLocator
        .instanceOf(GenericRepository.class);

    ReportesHeladerasRepository reportesRepository =
        RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);

    IncidentesService incidentesService =
        ServiceLocator.instanceOf(IncidentesService.class);

    BuscadorTecnicosCercanos buscadorTecnicosCercanos =
        UtilsLocator.instanceOf(BuscadorTecnicosCercanos.class);

    List<SensorTemperatura> sensores = genericRepository.buscarTodos(SensorTemperatura.class);

    for (SensorTemperatura sensor : sensores) {

      Heladera heladera = sensor.getHeladera();

      if (!sensor.estaConectado()
          && incidentesService.noFueAlertadoPor(TipoEstado.INACTIVA_FALLA_CONEXION, heladera)) {

        Incidente incidente = new Incidente(TipoIncidente.ALERTA, heladera);
        incidente.setTipoAlerta(TipoEstado.INACTIVA_FALLA_CONEXION);
        genericRepository.guardar(incidente);

        heladera.modificarEstado(TipoEstado.INACTIVA_FALLA_CONEXION);

        buscadorTecnicosCercanos.notificarTecnicos(heladera);

        heladera.intentarNotificarSuscriptores(TipoSuscripcion.OCURRIO_DESPERFECTO);

        ReporteHeladera reporte =
            reportesRepository.buscarSemanalPorHeladera(heladera.getId()).get();
        reporte.ocurrioUnaFalla();
        genericRepository.modificar(reporte);
      }
    }
  }
}