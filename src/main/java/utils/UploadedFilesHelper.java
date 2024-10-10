package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UploadedFilesHelper {
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

  public static String getImageFromContext(Context context) {
    UploadedFile file = context.uploadedFile("imagen");
    String imagePath = null;

    if (file != null) {
      String staticPath = "uploaded-imgs/" + file.filename();
      try {
        File directory = new File(staticPath);
        FileUtils.copyInputStreamToFile(file.content(), directory);
        imagePath = "/" + staticPath;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return imagePath;
  }
}
