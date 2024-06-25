package models.entities.heladera.sensores.temperatura;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import models.repositories.imp.HeladerasRepository;

/**
 * .
 */

public class FallaDesconexionDetector {

  /**
   * Main para el CronJob.
   */

  public static void main(String[] args) {
    List<Heladera> heladeras = HeladerasRepository.getInstance().getHeladeras();
    for (Heladera heladera : heladeras) {
      SensorTemperatura sensor = heladera.getModelo().getSensorTemperatura();
      if (sensor.fallaConexion()) {
        heladera.modificarEstado(TipoEstado.INACTIVA_FALLA_CONEXION);
      }
    }
  }
}
