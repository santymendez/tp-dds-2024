package models.entities.heladera.sensores.temperatura;

import config.RepositoryLocator;
import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesRepository;

/**
 * Representa al detector de la falla de conexion entre la heladera
 * y el sensor de temperatura.
 */

public class DetectorFallaDesconexion {

  /**
   * Main para el CronJob encargado de la revision de la conexion.
   */

  public static void main(String[] args) {
    GenericRepository repoGenerico = RepositoryLocator
            .get("genericRepository", GenericRepository.class);

    ReportesRepository reportesRepository =
        RepositoryLocator.get("reportesRepository", ReportesRepository.class);

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
        ReporteHeladera reporte = reportesRepository.buscarSemanalPorHeladera(heladera.getId());
        reporte.ocurrioUnaFalla();
        heladera.intentarNotificarSuscriptores();

      }
    }
  }
}