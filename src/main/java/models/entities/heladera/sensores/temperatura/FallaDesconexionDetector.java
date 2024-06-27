package models.entities.heladera.sensores.temperatura;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import models.repositories.heladera.HeladerasRepository;
import models.repositories.heladera.InterfaceHeladerasRepository;
import utils.sender.SenderLocator;

/**
 * .
 */

public class FallaDesconexionDetector {

  private static InterfaceHeladerasRepository heladerasRepository;

  public FallaDesconexionDetector(InterfaceHeladerasRepository heladerasRepository) {
    FallaDesconexionDetector.heladerasRepository = heladerasRepository;
  }

  /**
   * Main para el CronJob.
   */

  public static void main(String[] args) {
    List<Heladera> heladeras = heladerasRepository.obtenerHeladeras();
    for (Heladera heladera : heladeras) {
      SensorTemperatura sensor = heladera.getModelo().getSensorTemperatura();
      if (sensor.fallaConexion()) {
        heladera.reportarIncidente(TipoEstado.INACTIVA_FALLA_CONEXION);
      }
    }
  }
}
