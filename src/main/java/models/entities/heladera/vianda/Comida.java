package models.entities.heladera.vianda;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.converters.LocalDateAttributeConverter;

/**
 * Representa una comida con un nombre y una fecha de vencimiento.
 */

@Getter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Comida {

  @Column(name = "nombreComida", nullable = false)
  private String nombre;

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "vencimiento", nullable = false)
  private LocalDate vencimiento;
}
