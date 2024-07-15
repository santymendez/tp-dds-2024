package models.entities.heladera.sensores.temperatura;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.repositories.heladera.InterfaceHeladerasRepository;

/**
 * Representa al detector de la falla de conexion entre la heladera
 * y el sensor de temperatura.
 */

public class FallaDesconexionDetector {

  private static InterfaceHeladerasRepository heladerasRepository;

  public FallaDesconexionDetector(InterfaceHeladerasRepository heladerasRepository) {
    FallaDesconexionDetector.heladerasRepository = heladerasRepository;
  }

  /**
   * Main para el CronJob encargado de la revision de la conexion.
   */

  public static void main(String[] args) {
    List<Heladera> heladeras = heladerasRepository.obtenerHeladeras();
    for (Heladera heladera : heladeras) {
      SensorTemperatura sensor = heladera.getModelo().getSensorTemperatura();
      if (sensor.fallaConexion()) {
        heladera.getModIncidentes().reportarIncidente(TipoEstado.INACTIVA_FALLA_CONEXION, heladera);
      }
    }
  }
}