package models.entities.tarjetas.vulnerable;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import models.entities.heladera.Heladera;

/**
 * Representa un registro de uso con una fecha de utilización y una heladera.
 */

@Getter
@AllArgsConstructor
public class RegistroUso {
  private LocalDate fechaUtilizacion;
  private Heladera heladera;
}
