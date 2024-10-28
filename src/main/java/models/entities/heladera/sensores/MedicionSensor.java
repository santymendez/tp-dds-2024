package models.entities.heladera.sensores;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateTimeAttributeConverter;
import models.db.Persistente;
import models.entities.heladera.Heladera;

/**
 * Clase que representa los datos registrados al recibir una medición de temperatura.
 */

@Getter
@Setter
@Entity
@Table(name = "mediciones_sensores")
@NoArgsConstructor
public class MedicionSensor extends Persistente {
  
  @Column(name = "valor")
  private Float valor;

  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name = "fecha")
  private LocalDateTime fechaYhora;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id", nullable = false)
  private Heladera heladera;

  /**
   * Metodo constructor de las mediciones de los sensores.
   *
   * @param valor representa el valor numerico tomado por el sensor en caso de ser.
   * @param heladera es la heladera que esta relacionada con la medicion.
   */

  public MedicionSensor(Float valor, Heladera heladera) {
    this.valor = valor;
    this.fechaYhora = LocalDateTime.now();
    this.heladera = heladera;
  }

  @PrePersist
  protected void onInsert() {
    if (this.fechaYhora == null) {
      this.fechaYhora = LocalDateTime.now();
    }
  }
}
