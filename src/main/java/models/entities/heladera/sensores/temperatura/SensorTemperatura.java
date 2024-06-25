package models.entities.heladera.sensores.temperatura;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas máximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura implements IMqttMessageListener {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private MedicionSensorTemp ultMedicion;
  private String topic = "dds2024/heladeras/almagro/medrano";
  private Heladera heladera;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    String mensaje = mqttMessage.toString();
    Float temp = Float.parseFloat(mensaje);
    this.ultMedicion = new MedicionSensorTemp(temp);
    this.activarSensor();
  }
  //TODO main del cronjob
  //TODO ver si queremos tener una lista de mediciones
  //TODO decirme que les parece la nueva clase

  /**
   * Función que desactiva el sensor si la temperatura no está en el rango.
   */

  public void activarSensor() {
    if (ultMedicion.getTemperatura() > temperaturaMaxima
        || ultMedicion.getTemperatura() < temperaturaMinima) {
      this.desactivarHeladera();
    }
  }

  public void desactivarHeladera() {
    this.heladera.modificarEstado(TipoEstado.INACTIVA_TEMPERATURA);
    this.heladera.reportarIncidente(TipoEstado.INACTIVA_TEMPERATURA);
  }

  public void activarHeladera() {
    this.heladera.modificarEstado(TipoEstado.ACTIVA);
  }

}
