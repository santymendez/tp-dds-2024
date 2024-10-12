package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Clase Helper para manejar archivos subidos por el usuario.
 */

public class UploadedFilesHelper {

  /**
   * Obtiene un archivo CSV subido por el usuario desde el contexto.
   *
   * @param context Contexto de la petición HTTP.
   * @return Lista de arreglos de Strings con las líneas del archivo CSV.
   */

  public static List<String[]> getCsvFromContext(Context context) {
    UploadedFile file = context.uploadedFile("csv");
    List<String[]> allLines = null;
    if (file != null) {
      try {
        CSVReader reader = new CSVReader((new InputStreamReader((file.content()))));
        allLines = reader.readAll();
      } catch (IOException | CsvException e) {
        throw new RuntimeException(e);
      }
    }
    return allLines;
  }

  /**
   * Obtiene una imagen subida por el usuario desde el contexto.
   *
   * @param context Contexto de la petición HTTP.
   * @return Ruta de la imagen subida por el usuario.
   */

  public static String getImageFromContext(Context context) {
    UploadedFile file = context.uploadedFile("imagen");
    String imagePath = null;

    if (file != null) {
      if (!file.filename().isEmpty()) {
        String staticPath = "uploaded-imgs/" + file.filename();
        try {
          File directory = new File(staticPath);
          FileUtils.copyInputStreamToFile(file.content(), directory);
          imagePath = "/" + staticPath;
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        imagePath = "/static-imgs/logo.png";
      }
    }

    return imagePath;
  }
}
