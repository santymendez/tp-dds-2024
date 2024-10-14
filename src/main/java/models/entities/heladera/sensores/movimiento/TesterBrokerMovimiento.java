package models.entities.heladera.sensores.movimiento;

/**
 * Tester para el Broker de los Sensores de Movimiento.
 */

public class TesterBrokerMovimiento {
  public static void main(String[] args) {
    BrokerSensorMovimiento b = new BrokerSensorMovimiento();
    b.suscribir("sensores/movimiento", "movId");
  }
}
