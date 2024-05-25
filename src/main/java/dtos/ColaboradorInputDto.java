package dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Misma idea que el ColaboracionInputDto.
 */

@Getter
@Setter
public class ColaboradorInputDto {
  private String tipoDocumento;
  private Integer numeroDocumento;
  private String nombre;
  private String apellido;
  private String email;
  private String fecha;
  private String tipoColaboracion;
  private Integer cantidad;
}
