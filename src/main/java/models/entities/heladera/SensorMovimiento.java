package models.entities.heladera;

/**
 * Representa un sensor de movimiento.
 */

public class SensorMovimiento implements Sensor {

  /**
   * Método que recibe la heladera y te activa el sensor si la temperatura no está en el rango.
   */

  @Override
  public void activarSensor(Heladera heladera) {
    if (!heladera.getEstaAbierta()) {
      this.desactivarHeladera(heladera);
    }
  }

  @Override
  public void desactivarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.INACTIVA_FRAUDE);
    heladera.imprimirAlerta();
  }

  @Override
  public void activarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.ACTIVA);
  }
}