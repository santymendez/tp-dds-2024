package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  DTO de la donacion de una vianda.
 */

@Data
@AllArgsConstructor
public class DonacionViandasDto {
  private String viandasDonadas; // TODO ver que hacer con la lista

  private String cantViandas;
}
