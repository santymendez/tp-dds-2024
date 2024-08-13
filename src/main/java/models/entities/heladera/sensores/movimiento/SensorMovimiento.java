package models.entities.heladera.sensores.movimiento;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.sensores.MedicionSensor;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
public class SensorMovimiento implements IMqttMessageListener {
  private Integer id;
  private List<MedicionSensor> mediciones; //Para guardar todas las activaciones del sensor
  private Heladera heladera;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {}

  public void recibirMedicion(MedicionSensor medicion) {
    this.mediciones.add(medicion);
  }

  /**
   * Metodo que recibe la heladera y te activa el sensor si la temperatura no está en el rango.
   */
  
  public Boolean debeActivarSensor() {
    return !this.heladera.getEstaAbierta();
  }

  public void desactivarHeladera(Incidente incidente) {
    this.heladera.getModEstados().modificarEstado(TipoEstado.INACTIVA_FRAUDE);
    this.heladera.getModIncidentes().reportarIncidente(incidente);
  }

  public void activarHeladera() {
    this.heladera.getModEstados().modificarEstado(TipoEstado.ACTIVA);
  }
}