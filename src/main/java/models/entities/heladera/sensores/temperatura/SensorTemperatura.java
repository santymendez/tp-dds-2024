package models.entities.heladera.sensores.temperatura;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.sensores.MedicionSensor;

/**
 * Representa un sensor de temperatura con la última temperatura.
 */

@Getter
@Setter
@Entity
@Table(name = "sensores_temperatura")
public class SensorTemperatura extends Persistente {

  @OneToMany
  @JoinColumn(name = "medicionesSensor_id")
  private List<MedicionSensor> mediciones;

  @OneToOne
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
    LocalDateTime fecha = this.mediciones.get(0).getFecha();
    return this.periodoEnMinutos(fecha) > 5;
  }

  //================================== Métodos auxiliares =========================================

  private Long periodoEnMinutos(LocalDateTime fecha) {
    LocalDateTime instanteActual = LocalDateTime.now();
    return ChronoUnit.MINUTES.between(fecha, instanteActual);
  }
}
