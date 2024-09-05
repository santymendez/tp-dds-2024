package models.entities.heladera.sensores.movimiento;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
@Entity
@Table(name = "sensores_movimiento")
public class SensorMovimiento extends Persistente {

  @OneToMany(cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "sensorMovimiento_id", referencedColumnName = "id")
  private List<MedicionSensor> mediciones;

  @OneToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id", nullable = false)
  private Heladera heladera;

  public void recibirMedicion(MedicionSensor medicion) {
    this.mediciones.add(medicion);
  }
  
  public Boolean debeActivarSensor() {
    return !this.heladera.getEstaAbierta();
  }

  public void desactivarHeladera(Incidente incidente) {
    this.heladera.modificarEstado(TipoEstado.INACTIVA_FRAUDE);
  }

  public void activarHeladera() {
    this.heladera.modificarEstado(TipoEstado.ACTIVA);
  }
}