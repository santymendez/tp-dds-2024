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
//Permite recibir una nueva última temperatura
//También permite modificar las temperaturas max y min
public class SensorTemperatura {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultTemperatura;

  /**
   * Función que recibe la heladera y te activa el sensor si la temperatura no está en el rango.
   */

  public void recibirMedicion(Heladera heladera, Float ultTemperatura) {
    if (ultTemperatura > temperaturaMaxima || ultTemperatura < temperaturaMinima) {
      this.activarSensor(heladera);
      heladera.generarAlerta();
    }
    this.setUltTemperatura(ultTemperatura);
  }

  public void activarSensor(Heladera heladera) {
    this.modificarEstado(heladera, TipoEstado.INACTIVA_TEMPERATURA);
    heladera.generarAlerta();
  }

  public void desactivarSensor(Heladera heladera) {
    this.modificarEstado(heladera, TipoEstado.ACTIVA);
  }

  /**
   * Setea la fecha final del estado anterior y crea el nuevo estado actual.
   */

  public void modificarEstado(Heladera heladera, TipoEstado estado) {
    heladera.getEstadoActual().setFechaFinal(LocalDate.now());
    heladera.setEstadoActual(new Estado(estado));
    heladera.getEstadoActual().setFechaInicial(LocalDate.now());
    heladera.getEstadosHeladera().add(heladera.getEstadoActual());
  }
}
