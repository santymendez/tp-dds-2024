package models.entities.heladera.sensores.temperatura;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.reporte.ReporteHeladera;
import models.repositories.RepositoryLocator;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.interfaces.InterfaceIncidentesRepository;
import models.repositories.interfaces.InterfaceReportesRepository;
import models.repositories.interfaces.InterfaceSensoresTemperaturaRepository;

/**
 * Representa al detector de la falla de conexion entre la heladera
 * y el sensor de temperatura.
 */

public class DetectorFallaDesconexion {

  /**
   * Main para el CronJob encargado de la revision de la conexion.
   */

  public static void main(String[] args) {
    InterfaceSensoresTemperaturaRepository sensoresTemperaturaRepository = RepositoryLocator
            .get("sensoresTemperaturaRepository", SensoresTemperaturaRepository.class);

    InterfaceIncidentesRepository incidentesRepository =
        RepositoryLocator
            .get("incidentesRepository", IncidentesRepository.class);

    InterfaceReportesRepository reportesRepository =
        RepositoryLocator.get("reportesRepository", ReportesRepository.class);

    List<SensorTemperatura> sensores = sensoresTemperaturaRepository.buscarTodos();
    for (SensorTemperatura sensor : sensores) {
      if (!sensor.estaConectado()) {
        Heladera heladera = sensor.getHeladera();

        //Se registra el incidente
        Incidente incidente = new Incidente(TipoIncidente.ALERTA, heladera);
        incidente.setTipoAlerta(TipoEstado.INACTIVA_FALLA_CONEXION);
        incidentesRepository.guardar(incidente);

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