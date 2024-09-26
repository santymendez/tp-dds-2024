package dtos;

import lombok.Builder;
import lombok.Data;

/**
 * Representa el input de una direccion.
 */

@Data
@Builder
public class DireccionInputDto {
  private String nombreUbicacion;
  private String longitud;
  private String latitud;

  //Barrio
  private String nombreBarrio;
  private String calle;
  private String numero;

  //Ciudad
  private String nombreCiudad;

  //Provincia
  private String nombreProvincia;
}
