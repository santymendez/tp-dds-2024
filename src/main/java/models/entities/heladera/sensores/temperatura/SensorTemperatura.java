package models.entities.heladera.sensores.temperatura;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.sensores.MedicionSensor;

/**
 * Representa un sensor de temperatura con la última temperatura.
 */

@Getter
@Setter
public class SensorTemperatura {
  private Integer id;
  private List<MedicionSensor> mediciones;
  private Heladera heladera;

  public void recibirMedicion(MedicionSensor medicion) {
    this.mediciones.add(medicion);
  }

  /**
   * Comprueba si la temperatura esta fuera del rango.
   */

  public Boolean comprobarTemperatura(Float temperatura) {
    float tempMin = this.heladera.getModelo().getTemperaturaMinima();
    float tempMax = this.heladera.getModelo().getTemperaturaMaxima();

    return temperatura > tempMax || temperatura < tempMin;
  }

  public void desactivarHeladera(Incidente incidente) {
    this.heladera.getModEstados().modificarEstado(TipoEstado.INACTIVA_TEMPERATURA);
    this.heladera.getModIncidentes().reportarIncidente(incidente);
  }

  public void activarHeladera() {
    this.heladera.getModEstados().modificarEstado(TipoEstado.ACTIVA);
  }

  public Boolean estaConectado() {
    LocalDateTime fecha = mediciones.get(0).getFecha();
    return this.periodoEnMinutos(fecha) > 5;
  }

  //================================== Metodos auxiliares =========================================

  private Long periodoEnMinutos(LocalDateTime fecha) {
    LocalDateTime instanteActual = LocalDateTime.now();
    return ChronoUnit.MINUTES.between(fecha, instanteActual);
  }
}
