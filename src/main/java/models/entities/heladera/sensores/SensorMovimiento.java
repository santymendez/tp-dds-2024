package models.entities.heladera.sensores;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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

/**
 * Representa un sensor de movimiento.
 */

@Getter
@Setter
@Entity
@Table(name = "sensores_movimiento")
public class SensorMovimiento extends Persistente {

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "sensorMovimiento_id", referencedColumnName = "id")
  private List<MedicionSensor> mediciones;

  @OneToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id", nullable = false)
  private Heladera heladera;

  public SensorMovimiento() {
    this.mediciones = new ArrayList<>();
  }

  public void recibirMedicion(MedicionSensor medicion) {
    this.mediciones.add(medicion);
  }
  
  public Boolean debeActivarSensor() {
    return !this.heladera.getEstaAbierta();
  }

  public void desactivarHeladera() {
    this.heladera.modificarEstado(TipoEstado.INACTIVA_FRAUDE);
  }
}