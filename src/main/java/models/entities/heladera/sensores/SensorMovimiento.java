package models.entities.heladera.sensores;

import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;

/**
 * Representa un sensor de movimiento.
 */

public class SensorMovimiento {

  /**
   * Método que recibe la heladera y te activa el sensor si la temperatura no está en el rango.
   */

  public void activarSensor(Heladera heladera) {
    if (!heladera.getEstaAbierta()) {
      this.desactivarHeladera(heladera);
    }
  }

  public void desactivarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.INACTIVA_FRAUDE);
    heladera.imprimirAlerta();
  }

  public void activarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.ACTIVA);
  }
}