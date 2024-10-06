package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO para donacion de dinero.
 */

@Data
@AllArgsConstructor
public class DonacionDineroDto {
  private String montoDonado;
  private String frecuencia;
}
