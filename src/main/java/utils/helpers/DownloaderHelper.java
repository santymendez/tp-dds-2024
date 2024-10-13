package utils.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Helper para la descarga de informes.
 */

public class DownloaderHelper {

  /**
   * Descarga los informes dentro del rango de fechas especificado
   * y los comprime en un solo archivo ZIP.
   *
   * @return El archivo ZIP creado que contiene los informes.
   */

  public static File zip(List<String> paths) {
    File archivoZip = new File("reportes.zip");
    try {
      // Crear el archivo ZIP
      FileOutputStream fos = new FileOutputStream(archivoZip);
      ZipOutputStream zipOut = new ZipOutputStream(fos);

      // Recorrer cada archivo y añadirlo al ZIP
      for (String archivoPath : paths) {
        File archivo = new File(archivoPath);
        if (archivo.exists() && !archivo.isDirectory()) {
          // Asegurarse de que el archivo exista y no sea un directorio
          System.out.println("Añadiendo archivo al zip: " + archivoPath);

          FileInputStream fis = new FileInputStream(archivo);
          ZipEntry zipEntry = new ZipEntry(archivo.getName());
          // Crear una entrada ZIP para cada archivo
          zipOut.putNextEntry(zipEntry);

          // Leer y escribir los contenidos del archivo en el ZIP
          byte[] bytes = new byte[1024];
          int length;
          while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
          }

          // Cerrar la entrada ZIP para el archivo actual
          zipOut.closeEntry();
          fis.close();
        } else {
          System.out.println("El archivo no existe o es un directorio: " + archivoPath);
        }
      }

      // Cerrar el flujo del ZIP
      zipOut.close();
      fos.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    // Devolver el archivo zip creado
    return archivoZip;
  }
}
