package models.entities.heladera;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas maximas
 * y minimas.
 */

@Getter
@Setter //Permite recibir una nueva última temperatura
//También permite modificar las temperaturas max y min
public class SensorTemperatura {
  private Heladera heladera;
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultimaTemperatura;

  public void recibirMedicion() {}

  public void activarSensor() {
    this.heladera.setMesesActiva(this.heladera.calcularMesesActiva());
    this.heladera.setEstaActiva(false);
  }

  public void desactivarSensor() {
    this.heladera.setUltVezActivada(LocalDate.now());
    this.heladera.setEstaActiva(true);
  }
}
