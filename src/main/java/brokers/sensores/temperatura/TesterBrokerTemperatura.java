package brokers.sensores.temperatura;

/**
 * Tester para el Broker de los Sensores de Temperatura.
 */

public class TesterBrokerTemperatura {
  public static void main(String[] args) {
    BrokerSensorTemperatura b = new BrokerSensorTemperatura();
    b.suscribir("sensores/temperatura", "tempId");
  }
}
