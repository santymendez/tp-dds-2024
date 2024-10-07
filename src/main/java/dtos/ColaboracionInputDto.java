package dtos;

import lombok.Data;

/**
 * Representa el Input de una Colaboracion.
 */

@Data
public class ColaboracionInputDto {
  private String fecha;
  private String tipoColaboracion;
  private String cantidad;
  private String colaborador;

  /**
   * Crea un objeto ColaboracionInputDto a partir de un array de Strings.
   *
   * @param line Array de Strings.
   * @return ColaboracionInputDto.
   */

  public static ColaboracionInputDto fromCsv(String[] line) {
    ColaboracionInputDto colaboracionInputDto = new ColaboracionInputDto();
    colaboracionInputDto.setFecha(line[5]);
    colaboracionInputDto.setTipoColaboracion(line[6]);
    colaboracionInputDto.setCantidad(line[7]);
    return colaboracionInputDto;
  }
}
