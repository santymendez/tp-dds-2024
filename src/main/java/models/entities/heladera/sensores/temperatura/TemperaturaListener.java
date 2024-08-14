package models.entities.heladera.sensores.temperatura;

import java.util.Optional;
import lombok.Setter;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.MedicionSensor;
import models.repositories.interfaces.InterfaceIncidentesRepository;
import models.repositories.interfaces.InterfaceSensoresTemperaturaRepository;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import models.repositories.RepositoryLocator;

/**
 * Clase que representa al listener del sensor de temperatura para el broker.
 */
@Setter
public class TemperaturaListener implements IMqttMessageListener {
  private String topic = "sensores/temperatura";

  @Override
  public void messageArrived(String topic, MqttMessage message) throws Exception {
    int sensorId = Integer.parseInt(message.toString().split(";")[0]);
    Float temp = Float.parseFloat(message.toString().split(";")[1]);
    this.enviarMedicion(sensorId, temp);
  }

  /**
   * Metodo que envia la medicion recibida al sensor de temperatura.
   *
   * @param sensorId id que representa al sensor.
   * @param temp medicion a ser enviada.
   */

  public void enviarMedicion(int sensorId, Float temp) {
    InterfaceSensoresTemperaturaRepository sensoresTemperaturaRepository =
        (InterfaceSensoresTemperaturaRepository) RepositoryLocator
            .get("sensoresTemperaturaRepository");

    InterfaceIncidentesRepository incidentesRepository =
        (InterfaceIncidentesRepository) RepositoryLocator
            .get("incidentesRepository");

    Optional<SensorTemperatura> sensor =
        sensoresTemperaturaRepository.buscar(sensorId);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }

    SensorTemperatura sensorTemperatura = sensor.get();

    MedicionSensor medicion = new MedicionSensor(temp, sensorTemperatura.getHeladera());
    sensorTemperatura.recibirMedicion(medicion);

    if (!sensorTemperatura.comprobarTemperatura(temp)) {
      Incidente incidente = new Incidente(TipoIncidente.ALERTA, sensorTemperatura.getHeladera());
      incidente.setTipoAlerta(TipoEstado.INACTIVA_TEMPERATURA);
      sensorTemperatura.desactivarHeladera(incidente);

      incidentesRepository.guardar(incidente);
    }
  }
}
