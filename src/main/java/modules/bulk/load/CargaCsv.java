package modules.bulk.load;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modules.email.sender.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Carga del contenido del archivo csv.
 */
public class CargaCsv {

  private static final Logger logger = LogManager.getLogger(EmailSender.class);
  private static final String ruta_archivo = "src/main/resources/lista_colaboradores.csv";

  /**
   * Lectura del archivo csv.
   *
   */

  public List<ColaboradorRepository> leerArchivoCsv() {
    List<ColaboradorRepository> colaboradores = new ArrayList<>();

    try {
      CSVReader reader = new CSVReader(new FileReader(CargaCsv.ruta_archivo));
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        ColaboradorRepository colaborador = new ColaboradorRepository();
        colaborador.setTipoDocumento(nextLine[0]);
        colaborador.setNumeroDocumento(nextLine[1]);
        colaborador.setNombre(nextLine[2]);
        colaborador.setApellido(nextLine[3]);
        colaborador.setEmail(nextLine[4]);
        colaborador.setFecha(nextLine[5]);
        colaborador.setTipoColaboracion(nextLine[6]);
        colaborador.setCantidad(Integer.parseInt(nextLine[7]));
        colaboradores.add(colaborador);
      }
    } catch (FileNotFoundException e) {
      logger.error("Archivo no encontrado: ", e);
      throw new RuntimeException("Archivo no encontrado: " + e.getMessage());
    } catch (IOException e) {
      logger.error("Error abriendo el archivo: ", e);
      throw new RuntimeException("Error abriendo el archivo: " + e.getMessage());
    } catch (CsvValidationException e) {
      logger.error("Archivo CSV no válido: ", e);
      throw new RuntimeException("Archivo CSV no válido: " + e.getMessage());
    }

    return colaboradores;
  }
}
