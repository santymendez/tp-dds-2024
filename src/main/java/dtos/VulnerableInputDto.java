package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO para el ingreso de datos de un vulnerable.
 */

@Data
@AllArgsConstructor
public class VulnerableInputDto {
  String nombre;
  String fechaNacimiento;
  String tipoDocumento;
  String numeroDocumento;
  String provincia;
  String cuidad;
  String barrio;
  String calle;
  String numero;
  String cantMenores;
}
