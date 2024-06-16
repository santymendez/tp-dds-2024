package models.entities.heladera.sensores.temperatura;

import models.entities.heladera.sensores.temperatura.mqttclient.MyCustomMessageReceptor;

/**
 * Clase adapter para la recepcion del mensaje.
 */

public class AdapterReceptorMensaje {

  private SensorTemperatura sensor;
  private MyCustomMessageReceptor receptor;
  Float temperatura = 0f; // Inicializado


  //TODO: ver lo de recibir mensaje
  //TODO agregar a DC

  /**
   * Recibe la ultima temperatura.
   *
   * @return La ultima temperatura.
   */

  public float recibirUltimaTemperatura() {
    // receptor.messageArrived(); recibir ultima temperatura
    String mensaje = receptor.getUltimoMensaje().toString();
    temperatura = Float.parseFloat(mensaje);
    return temperatura;
  }

  public void cargarSensor() {
    sensor.setUltTemperatura(this.recibirUltimaTemperatura());
  }

}
