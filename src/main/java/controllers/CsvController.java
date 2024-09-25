package controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dtos.ColaboracionInputDto;
import dtos.ColaboradorInputDto;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.contacto.TipoContacto;
import models.factories.FactoryColaboracion;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ColaboradoresService;
import utils.sender.channels.EmailSender;

/**
 * Carga del contenido del archivo csv.
 */

public class CsvController {

  private static final Logger logger = LogManager.getLogger(EmailSender.class);
  private static final String ruta_archivo = "src/main/resources/lista_colaboradores.csv";
  private final ColaboradoresService colaboradoresService;
  private final ColaboradoresRepository colaboradoresRepository;
  private final ColaboracionesRepository colaboracionesRepository;

  /**
   * Constructor para el CSV Controller.
   *
   * @param colaboradoresService Es el Service de los Colaboradores.
   */

  public CsvController(
      ColaboradoresService colaboradoresService,
      ColaboradoresRepository colaboradoresRepository,
      ColaboracionesRepository colaboracionesRepository
  ) {
    this.colaboradoresService = colaboradoresService;
    this.colaboradoresRepository = colaboradoresRepository;
    this.colaboracionesRepository = colaboracionesRepository;
  }

  /**
   * Lectura del archivo CSV.
   *
   */

  public void leerArchivoCsv() {

    try {
      CSVReader reader = new CSVReader(new FileReader(CsvController.ruta_archivo));
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        ColaboradorInputDto colaboradorInputDto = new ColaboradorInputDto();
        colaboradorInputDto.setTipoDocumento(nextLine[0]);
        colaboradorInputDto.setNumeroDocumento(nextLine[1]);
        colaboradorInputDto.setNombre(nextLine[2]);
        colaboradorInputDto.setApellido(nextLine[3]);
        colaboradorInputDto.setContacto(nextLine[4]);
        colaboradorInputDto.setTipoContacto(TipoContacto.MAIL.toString());
        colaboradorInputDto.setTipoColaborador(TipoColaborador.FISICO.toString());

        ColaboracionInputDto colaboracionInputDto = new ColaboracionInputDto();
        colaboracionInputDto.setFecha(nextLine[5]);
        colaboracionInputDto.setTipoColaboracion(nextLine[6]);
        colaboracionInputDto.setCantidad(Integer.parseInt(nextLine[7]));

        this.crear(colaboradorInputDto, colaboracionInputDto);
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
    Colaborador unColaborador = this.crear(colaboradorInputDto);
    Colaboracion unaColaboracion = FactoryColaboracion.crearCon(colaboracionInputDto);
    unaColaboracion.setColaborador(unColaborador);

    unColaborador.getColaboraciones().add(unaColaboracion);
    unColaborador.aumentarReconocimiento(unaColaboracion);

    this.colaboracionesRepository.guardar(unaColaboracion);
  }

  /**
   * Metodo que crea un Colaborador.
   *
   * @param colaboradorInputDto Es el input que se utilizara para crear al colaborador.
   * @return El colaborador ya creado.
   */

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto) {

    Optional<Colaborador> unColaborador =
        this.colaboradoresRepository
            .buscarPorDocumento(Integer.valueOf(colaboradorInputDto.getNumeroDocumento()));

    return unColaborador
        .orElseGet(() -> this.colaboradoresService.crearDesdeCsv(colaboradorInputDto));
  }
}
