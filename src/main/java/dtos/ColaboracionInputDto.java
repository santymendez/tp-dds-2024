package dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Data;

/**
 * Representa el Input de una Colaboracion.
 */

@Data
public class ColaboracionInputDto {
  private LocalDate fecha;
  private String tipoColaboracion;
  private Integer cantidad;

  /**
   * Crea un objeto ColaboracionInputDto a partir de un array de Strings.
   *
   * @param line Array de Strings.
   * @return ColaboracionInputDto.
   */

  public static ColaboracionInputDto fromCsv(String[] line) {
    ColaboracionInputDto colaboracionInputDto = new ColaboracionInputDto();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    colaboracionInputDto.setFecha(LocalDate.parse(line[5], formatter));

    colaboracionInputDto.setTipoColaboracion(line[6]);
    colaboracionInputDto.setCantidad(Integer.valueOf(line[7]));
    return colaboracionInputDto;
  }
}
