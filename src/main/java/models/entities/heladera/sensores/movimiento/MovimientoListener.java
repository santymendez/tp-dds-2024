package models.entities.heladera.sensores.movimiento;

import java.util.Optional;
import lombok.Setter;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.MedicionSensor;
import models.repositories.interfaces.InterfaceIncidentesRepository;
import models.repositories.interfaces.InterfaceSensoresMovimientoRepository;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import models.repositories.RepositoryLocator;

/**
 * Clase que representa al listener del sensor de movimiento para el broker.
 */

@Setter
public class MovimientoListener implements IMqttMessageListener {
  private String topic = "sensores/movimiento";

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    int sensorId = Integer.parseInt(message.toString());
    this.activarSensor(sensorId);
  }

  private void activarSensor(int sensorId) {
    InterfaceSensoresMovimientoRepository sensoresMovimientoRepository =
        (InterfaceSensoresMovimientoRepository) RepositoryLocator
            .get("sensoresMovimientoRepository");

    InterfaceIncidentesRepository incidentesRepository =
        (InterfaceIncidentesRepository) RepositoryLocator
            .get("incidentesRepository");

    Optional<SensorMovimiento> sensor = sensoresMovimientoRepository.buscar(sensorId);
    if (sensor.isEmpty()) {
      throw new RuntimeException("No existe el sensor");
    }

    SensorMovimiento sensorMovimiento = sensor.get();

    if (sensorMovimiento.debeActivarSensor()) {
      MedicionSensor medicion = new MedicionSensor(null, sensorMovimiento.getHeladera());
      sensorMovimiento.recibirMedicion(medicion);

      Incidente incidente = new Incidente(TipoIncidente.ALERTA, sensorMovimiento.getHeladera());
      incidente.setTipoAlerta(TipoEstado.INACTIVA_FRAUDE);
      sensorMovimiento.desactivarHeladera(incidente);

      incidentesRepository.guardar(incidente);
    }
  }
}
