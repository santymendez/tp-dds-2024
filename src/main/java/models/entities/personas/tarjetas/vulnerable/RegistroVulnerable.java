package models.entities.personas.tarjetas.vulnerable;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Representa la información de registro que incluye al colaborador y al vulnerable.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_vulnerables")
public class RegistroVulnerable extends Persistente {
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @OneToOne
  @JoinColumn(name = "vulnerableRegistrado_id", referencedColumnName = "id")
  private Vulnerable vulnerable;

  @Column(name = "fechaRegistro", columnDefinition = "DATE")
  private LocalDate fechaRegistro;
}
