package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO para la creación de una heladera.
 */

@Data
@AllArgsConstructor
public class HeladeraInputDto {
  String nombre;
  String fechaCreacion;
  String modelo;
  String temperaturaMax;
  String temperaturaMin;
  String capacidadMaximaViandas;
  String nombreUbicacion;
  String latitud;
  String longitud;
  //HACE FALTA?
  String barrio;
  String calle;
  String numero;
  String ciudad;
  String provincia;
}
