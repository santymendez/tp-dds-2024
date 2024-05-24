package services.security.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una regla que verifica si una contraseña pertenece a un archivo.
 * La regla se cumple si la contraseña no se encuentra en el archivo.
 */

@Setter
@Getter
public class PerteneceAlArchivo extends Regla {
  private List<String> palabrasDelArchivo;

  @Override
  public Boolean cumpleLaRegla(String unaContrasenia) {
    return !this.getPalabrasDelArchivo().contains(unaContrasenia);
  }

  /**
   * Crea una nueva regla que verifica si una contraseña pertenece a un archivo.
   *
   * @param nombreArchivo el nombre del archivo a verificar
   * @param path la ruta del archivo a verificar
   */

  public PerteneceAlArchivo(String nombreArchivo, String path) {
    this.setPalabrasDelArchivo(new ArrayList<>());
    this.setMensajeError("Pertenece al archivo " + nombreArchivo);
    File archivo = new File(path);

    try {
      BufferedReader br = new BufferedReader(new FileReader(archivo));
      String linea;

      while ((linea = br.readLine()) != null) {

        // Dividir la línea en palabras utilizando espacio como separador
        String[] palabras = linea.split("\\s+");

        // Agregar cada palabra a la lista
        for (String palabra : palabras) {
          this.getPalabrasDelArchivo().add(palabra);
        }
      }

      br.close();
    } catch (IOException e) {
      System.err.println("Error al leer el archivo: " + e.getMessage());
    }
  }
}
