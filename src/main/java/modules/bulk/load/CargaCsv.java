package modules.bulk.load;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dtos.ColaboracionInputDto;
import dtos.ColaboradorInputDto;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import modules.email.sender.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ColaboracionesService;
import services.ColaboradoresService;

/**
 * Carga del contenido del archivo csv.
 */
public class CargaCsv {

  private static final Logger logger = LogManager.getLogger(EmailSender.class);
  private static final String ruta_archivo = "src/main/resources/lista_colaboradores.csv";
  private final ColaboradoresService colaboradoresService;
  private final ColaboracionesService colaboracionesService;

  public CargaCsv(
      ColaboradoresService colaboradoresService,
      ColaboracionesService colaboracionesService
  ) {
    this.colaboradoresService = colaboradoresService;
    this.colaboracionesService = colaboracionesService;
  }

  /**
   * Lectura del archivo CSV.
   *
   */

  public void leerArchivoCsv() {

    try {
      CSVReader reader = new CSVReader(new FileReader(CargaCsv.ruta_archivo));
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        ColaboradorInputDto colaboradorInputDto = new ColaboradorInputDto();
        colaboradorInputDto.setTipoDocumento(nextLine[0]);
        colaboradorInputDto.setNumeroDocumento(Integer.parseInt(nextLine[1]));
        colaboradorInputDto.setNombre(nextLine[2]);
        colaboradorInputDto.setApellido(nextLine[3]);
        colaboradorInputDto.setEmail(nextLine[4]);

        ColaboracionInputDto colaboracion = new ColaboracionInputDto();
        colaboracion.setFecha(nextLine[5]);
        colaboracion.setTipoColaboracion(nextLine[6]);
        colaboracion.setCantidad(Integer.parseInt(nextLine[7]));

        Colaborador unColaborador = colaboradoresService.crear(colaboradorInputDto);
        Colaboracion unaColaboracion = colaboracionesService.crear(colaboracion);

        unColaborador.aumentarReconocimiento(unaColaboracion);
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
  }
}
