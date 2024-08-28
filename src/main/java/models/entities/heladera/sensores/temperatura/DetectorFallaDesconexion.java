package models.entities.heladera.sensores.temperatura;

import java.util.List;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.repositories.RepositoryLocator;
import models.repositories.imp.IncidentesRepository;
import models.repositories.imp.SensoresTemperaturaRepository;
import models.repositories.interfaces.InterfaceIncidentesRepository;
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

    List<SensorTemperatura> sensores = sensoresTemperaturaRepository.buscarTodos();
    for (SensorTemperatura sensor : sensores) {
      if (!sensor.estaConectado()) {
        Incidente incidente = new Incidente(TipoIncidente.ALERTA, sensor.getHeladera());
        incidente.setTipoAlerta(TipoEstado.INACTIVA_FALLA_CONEXION);
        sensor.getHeladera().getModIncidentes().reportarIncidente(incidente);
        incidentesRepository.guardar(incidente);
      }
    }
  }
}