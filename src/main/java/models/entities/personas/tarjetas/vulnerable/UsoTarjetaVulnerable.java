package models.entities.personas.tarjetas.vulnerable;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.db.Persistente;
import models.entities.heladera.Heladera;

/**
 * Representa un registro de uso con una fecha de utilización y una heladera.
 */

@Getter
@NoArgsConstructor
@Entity
@Table(name = "usos_tarjetas_vulnerables")
public class UsoTarjetaVulnerable extends Persistente {

  @Column(name = "fechaUtilizacion", columnDefinition = "DATE")
  private LocalDate fechaUtilizacion;

  @ManyToOne
  @JoinColumn(name = "heladera_id")
  private Heladera heladera;
}
