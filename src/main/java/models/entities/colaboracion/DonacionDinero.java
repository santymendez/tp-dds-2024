package models.entities.colaboracion;

import lombok.Getter;
import lombok.Setter;

/**
 * Colaboracion para Donar Dinero.
 */

@Getter
@Setter
public class DonacionDinero {
  private Integer monto;
  private String frecuencia;
}
