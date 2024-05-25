package dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase de ColaboracionInput para setear atributos y el controller instancia.
 */

@Getter
@Setter
public class ColaboracionInputDto {
  private String fecha;
  private String tipoColaboracion;
  private Integer cantidad;
}
