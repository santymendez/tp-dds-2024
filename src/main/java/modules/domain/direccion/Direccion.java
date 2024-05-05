package modules.domain.direccion;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una dirección con una ubicación, longitud, latitud y provincia.
 */
@Getter
@Setter
public class Direccion {
  private String ubicacion;
  private Float longitud;
  private Float latitud;
  private Provincia provincia;
}
