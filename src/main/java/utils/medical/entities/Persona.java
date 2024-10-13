package utils.medical.entities;

import lombok.Getter;

/**
 * Clase que representa a una persona vista desde el
 * servicio medico al que nos integramos.
 */

@Getter
public class Persona {
  //Necesitamos que se llame asi para poder integrarnos
  //Usamos el suppress para evitar problemas con el checkstyle
  @SuppressWarnings("all")
  private String nombreYApellido;
}
