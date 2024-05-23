package modules.domain.tarjeta;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import modules.domain.heladera.Heladera;

/**
 * Representa un registro de uso con una fecha de utilización y una heladera.
 */

@Getter
@AllArgsConstructor
public class RegistroUso {
  private LocalDate fechaUtilizacion;
  private Heladera heladera;
}
