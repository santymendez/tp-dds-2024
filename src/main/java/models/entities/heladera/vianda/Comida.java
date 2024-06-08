package models.entities.heladera.vianda;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa una comida con un nombre y una fecha de vencimiento.
 */

@Getter
@AllArgsConstructor
public class Comida {
  private String nombre;
  private LocalDate vencimiento;
}
