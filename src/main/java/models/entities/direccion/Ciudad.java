package models.entities.direccion;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una ciudad con un nombre y un barrio.
 */

@Getter
@Setter
public class Ciudad {
  private String nombreCiudad;
  private Barrio barrio;
}
