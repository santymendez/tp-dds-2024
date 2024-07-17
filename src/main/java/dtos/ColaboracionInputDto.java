package dtos;

import lombok.Data;

/**
 * Representa el Input de una Colaboracion.
 */

@Data
public class ColaboracionInputDto {
  private String fecha;
  private String tipoColaboracion;
  private Integer cantidad;
}
