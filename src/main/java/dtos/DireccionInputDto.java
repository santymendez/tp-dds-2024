package dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa el input de una direccion.
 */

@Data
@Builder
@Setter
@Getter
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
  private String provincia;
}
