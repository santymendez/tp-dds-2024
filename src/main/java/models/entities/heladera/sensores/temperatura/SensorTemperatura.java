package models.entities.heladera.sensores.temperatura;

import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas máximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultTemperatura;

  public void recibirMedicion(Heladera heladera, Float ultTemperatura) {
    this.setUltTemperatura(ultTemperatura);
    this.activarSensor(heladera);
  }

  /**
   * Función que desactiva el sensor si la temperatura no está en el rango.
   */

  public void activarSensor(Heladera heladera) {
    if (ultTemperatura > temperaturaMaxima || ultTemperatura < temperaturaMinima) {
      this.desactivarHeladera(heladera);
    }
  }

  public void desactivarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.INACTIVA_TEMPERATURA);
    heladera.reportarIncidente(TipoEstado.INACTIVA_TEMPERATURA);
  }

  public void activarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.ACTIVA);
  }
}
