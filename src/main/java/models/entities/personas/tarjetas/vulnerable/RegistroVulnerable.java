package models.entities.personas.tarjetas.vulnerable;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Representa la información de registro que incluye al colaborador y al vulnerable.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_vulnerables")
public class RegistroVulnerable extends Persistente {
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "vulnerable_id", referencedColumnName = "id", nullable = false)
  private Vulnerable vulnerable;

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "fechaRegistro")
  private LocalDate fechaRegistro;

  @PrePersist
  protected void onInsert() {
    if (this.fechaRegistro == null) {
      this.fechaRegistro = LocalDate.now();
    }
  }
}
