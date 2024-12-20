package models.entities.personas.tarjetas.vulnerable;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
import models.db.Persistente;
import models.entities.heladera.Heladera;

/**
 * Representa un registro de uso con una fecha de utilización y una heladera.
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "usos_tarjetas_vulnerables")
public class UsoTarjetaVulnerable extends Persistente {

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "fechaUtilizacion")
  private LocalDate fechaUtilizacion;

  @ManyToOne
  @JoinColumn(name = "heladera_id", nullable = false)
  private Heladera heladera;

  @ManyToOne
  @JoinColumn(name = "tarjetaVulnerable_codigo", referencedColumnName = "id", nullable = false)
  private TarjetaVulnerable tarjetaVulnerable;
}
