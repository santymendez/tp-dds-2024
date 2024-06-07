package models.entities.heladera;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas máximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura implements Sensor {
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

  @Override
  public void activarSensor(Heladera heladera) {
    if (ultTemperatura > temperaturaMaxima || ultTemperatura < temperaturaMinima) {
      this.desactivarHeladera(heladera);
      heladera.imprimirAlerta();
    }
  }

  @Override
  public void desactivarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.INACTIVA_TEMPERATURA);
    heladera.imprimirAlerta();
  }

  @Override
  public void activarHeladera(Heladera heladera) {
    heladera.modificarEstado(TipoEstado.ACTIVA);
  }
}
