package cronjobs;

import config.RepositoryLocator;
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
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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

    GenericRepository repoGenerico = RepositoryLocator
        .instanceOf(GenericRepository.class);

    ReportesHeladerasRepository reportesRepository =
        RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);

    List<SensorTemperatura> sensores = repoGenerico.buscarTodos(SensorTemperatura.class);

    for (SensorTemperatura sensor : sensores) {

      if (!sensor.estaConectado()) {

        Heladera heladera = sensor.getHeladera();

        //Se registra el incidente
        Incidente incidente = new Incidente(TipoIncidente.ALERTA, heladera);
        incidente.setTipoAlerta(TipoEstado.INACTIVA_FALLA_CONEXION);
        repoGenerico.guardar(incidente);

        //Se modifica el estado de la heladera
        heladera.modificarEstado(TipoEstado.INACTIVA_FALLA_CONEXION);

        //Se reporta la falla
        ReporteHeladera reporte =
            reportesRepository.buscarSemanalPorHeladera(heladera.getId()).get();
        reporte.ocurrioUnaFalla();
        heladera.intentarNotificarSuscriptores(TipoSuscripcion.OCURRIO_DESPERFECTO);

      }
    }
  }
}