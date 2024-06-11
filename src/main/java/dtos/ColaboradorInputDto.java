package dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Misma idea que el ColaboracionInputDto.
 */

@Data
public class ColaboradorInputDto {
  private String tipoDocumento;
  private Integer numeroDocumento;
  private String nombre;
  private String apellido;
  private String email;
}
