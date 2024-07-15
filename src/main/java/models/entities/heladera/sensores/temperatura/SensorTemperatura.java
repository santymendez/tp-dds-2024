package models.entities.heladera.sensores.temperatura;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;

/**
 * Representa un sensor de temperatura con la última temperatura, las temperaturas máximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura {
  private Integer id;
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private List<MedicionSensorTemp> mediciones;
  private Heladera heladera;

  public void recibirMedicion(Float temp) {
    this.mediciones.add(new MedicionSensorTemp(temp));
    this.activarSensor(temp);
  }

  /**
   * Desactiva el sensor si la temperatura no está en el rango.
   */

  public void activarSensor(Float temperatura) {
    if (temperatura > temperaturaMaxima
        || temperatura < temperaturaMinima) {
      this.desactivarHeladera();
    }
  }

  public void desactivarHeladera() {
    this.heladera.getModEstados().modificarEstado(TipoEstado.INACTIVA_TEMPERATURA);
    this.heladera.getModIncidentes().reportarIncidente(TipoEstado.INACTIVA_TEMPERATURA, heladera);
  }

  public void activarHeladera() {
    this.heladera.getModEstados().modificarEstado(TipoEstado.ACTIVA);
  }

  public Boolean fallaConexion() {
    LocalDateTime fecha = mediciones.get(0).getFecha();
    return this.periodoEnMinutos(fecha) > 5;
  }

  //================================== Metodos auxiliares =========================================

  private Long periodoEnMinutos(LocalDateTime fecha) {
    LocalDateTime instanteActual = LocalDateTime.now();
    return ChronoUnit.MINUTES.between(fecha, instanteActual);
  }
}
