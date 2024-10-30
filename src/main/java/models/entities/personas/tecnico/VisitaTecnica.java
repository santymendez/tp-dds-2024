package models.entities.personas.tecnico;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.incidente.Incidente;

/**
 * Clase que representa la visita técnica que realiza un técnico a una heladera.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visitas_tecnicas")
public class VisitaTecnica extends Persistente {
  @ManyToOne
  @JoinColumn(name = "incidente_id", referencedColumnName = "id", nullable = false)
  private Incidente incidente;

  @Column(name = "fechaVisita", columnDefinition = "DATE", nullable = false)
  private LocalDate fechaVisita;

  @Column(name = "trabajoRealizado", columnDefinition = "TEXT")
  private String trabajoRealizado;

  @Column(name = "fotoVisita")
  private String fotoVisita;

  @Column(name = "incidenteSolucionado", nullable = false)
  private Boolean incidenteSolucionado;

  @ManyToOne
  @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
  private Tecnico tecnico;
}
