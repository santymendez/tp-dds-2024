package dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el ingreso de datos de un vulnerable.
 */

@Data
@NoArgsConstructor
public class VulnerableInputDto {
  String nombre;
  String fechaNacimiento;
  String tipoDocumento;
  String numeroDocumento;
  String provincia;
  String ciudad;
  String barrio;
  String calle;
  String numero;
  String cantMenores;
}
