package models.entities.direccion;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una provincia con un nombre y una ciudad.
 */
@Getter
@Setter
public class Provincia {
  private String nombreProvincia;
  private Ciudad ciudad;
}
