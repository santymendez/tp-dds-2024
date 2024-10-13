package utils.medical.entities;

import java.util.List;

/**
 * Clase que representa a un Barrio segun como
 * esta representado para su uso en el servicio medico.
 */

public class Barrio {
  private String nombre;
  private int cantPersonas;
  private List<Persona> personasParaDevolverJson;

  /**
   *  Metodo para que muestre los datos.
   */

  public void showData() {

    System.out.println("Nombre: " + nombre);
    System.out.println("CantPersonas: " + cantPersonas);

    for (Persona persona : personasParaDevolverJson) {
      System.out.println("Persona: " + persona.getNombreYApellido());
    }
  }
}
