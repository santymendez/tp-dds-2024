package controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dtos.ColaboracionInputDto;
import dtos.ColaboradorInputDto;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import models.entities.colaboracion.Colaboracion;
import models.factories.FactoryColaboracion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ColaboracionesService;
import services.ColaboradoresService;
import utils.sender.channels.EmailSender;

/**
 * Carga del contenido del archivo csv.
 */

public class OldCsvController {

  private static final Logger logger = LogManager.getLogger(EmailSender.class);
  private static final String ruta_archivo = "src/main/resources/lista_colaboradores.csv";
  private final EmailSender emailSender;
  private final ColaboradoresService colaboradoresService;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor para el CSV Controller.
   *
   * @param colaboradoresService Es el Service de los Colaboradores.
   */

  public OldCsvController(
      ColaboradoresService colaboradoresService,
      EmailSender emailSender,
      ColaboracionesService colaboracionesService
  ) {
    this.colaboradoresService = colaboradoresService;
    this.emailSender = emailSender;
    this.colaboracionesService = colaboracionesService;
  }

  /**
   * Lectura del archivo CSV.
   *
   */

  public void leerArchivoCsv() {

    try {
      CSVReader reader = new CSVReader(new FileReader(ruta_archivo));
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        ColaboradorInputDto colaboradorInputDto = ColaboradorInputDto.fromCsv(nextLine);
        ColaboracionInputDto colaboracionInputDto = ColaboracionInputDto.fromCsv(nextLine);

        Colaboracion colaboracion = this.colaboracionesService.crearDesdeCsv(colaboracionInputDto);

        this.colaboradoresService
            .crearDesdeCsv(colaboradorInputDto, this.emailSender, colaboracion);
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

  /** Metodo que crea las clases leidas del archivo CSV.
   *
   * @param colaboradorInputDto Son los datos obtenidos del Colaborador.
   * @param colaboracionInputDto Son los datos obtenidos de la Colaboracion.
   */

  public void crear(
      ColaboradorInputDto colaboradorInputDto,
      ColaboracionInputDto colaboracionInputDto
  ) {
    Colaboracion unaColaboracion = FactoryColaboracion.crearDesdeCsv(colaboracionInputDto);
    this.colaboradoresService.crearDesdeCsv(colaboradorInputDto, this.emailSender, unaColaboracion);
  }
}
