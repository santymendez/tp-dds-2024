package dtos;

import lombok.Data;

/**
 * Clase de ColaboracionInput para setear atributos y el controller instancia.
 */

@Data
public class ColaboracionInputDto {
  private String fecha;
  private String tipoColaboracion;
  private Integer cantidad;
}
