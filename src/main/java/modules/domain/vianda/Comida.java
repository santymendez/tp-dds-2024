package modules.domain.vianda;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa una comida con un nombre y una fecha de vencimiento.
 */

@Getter
@AllArgsConstructor
public class Comida {
  private String nombre;
  private Date vencimiento;
}
