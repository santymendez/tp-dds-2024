package models.entities.heladera.sensores.temperatura;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.repositories.InterfaceIncidentesRepository;
import models.repositories.heladera.InterfaceHeladerasRepository;
import models.repositories.heladera.InterfaceSensoresTemperaturaRepository;

/**
 * Representa al detector de la falla de conexion entre la heladera
 * y el sensor de temperatura.
 */

public class DetectorFallaDesconexion {

  private static InterfaceIncidentesRepository incidentesRepository;
  private static InterfaceSensoresTemperaturaRepository sensoresTemperaturaRepository;

  /**
   * Main para el CronJob encargado de la revision de la conexion.
   */

  public static void main(String[] args) {
    List<SensorTemperatura> sensores = sensoresTemperaturaRepository.obtenerSensores();
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