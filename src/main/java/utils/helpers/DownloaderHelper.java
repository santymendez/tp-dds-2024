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
      FileOutputStream fos = new FileOutputStream(archivoZip);
      ZipOutputStream zipOut = new ZipOutputStream(fos);

      paths = paths.stream().map(p -> p.startsWith("/") ? p.substring(1) : p).toList();

      for (String archivoPath : paths) {
        File archivo = new File(archivoPath);
        if (archivo.exists() && !archivo.isDirectory()) {
          FileInputStream fis = new FileInputStream(archivo);
          ZipEntry zipEntry = new ZipEntry(archivo.getName());

          zipOut.putNextEntry(zipEntry);

          byte[] bytes = new byte[1024];
          int length;
          while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
          }

          zipOut.closeEntry();
          fis.close();
        }
      }

      zipOut.close();
      fos.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return archivoZip;
  }
}
