package controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import dtos.ColaboracionInputDto;
import dtos.ColaboradorInputDto;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.contacto.TipoContacto;
import models.repositories.imp.ColaboradoresRepository;
import services.ColaboracionesService;
import services.ColaboradoresService;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.sender.channels.EmailSender;

/**
 * Csv controller 2.
 */

public class CsvController2 implements InterfaceCrudViewsHandler {

  private final EmailSender emailSender;
  private final ColaboradoresService colaboradoresService;
  private final ColaboradoresRepository colaboradoresRepository;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor para el CSV Controller.
   *
   * @param colaboradoresService Es el Service de los Colaboradores.
   */

  public CsvController2(
      ColaboradoresService colaboradoresService,
      ColaboradoresRepository colaboradoresRepository,
      ColaboracionesService colaboracionesService,
      EmailSender emailSender
  ) {
    this.colaboradoresService = colaboradoresService;
    this.colaboradoresRepository = colaboradoresRepository;
    this.emailSender = emailSender;
    this.colaboracionesService = colaboracionesService;
  }

  public void index(Context context) {

  }

  public void show(Context context) {

  }

  /**
   * Crea una vista para cargar un archivo CSV.
   *
   * @param context el contexto de la aplicación.
   */

  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Cargar CSV");
    model.put("activeSession", true);

    context.render("cargar-csv.hbs", model);
  }

  /**
   * Guarda los datos del archivo CSV en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void save(Context context) {
    UploadedFile file = context.uploadedFile("csv");
    if (file != null) {
      try {
        CSVReader reader = new CSVReader(new InputStreamReader(file.content()));
        List<String[]> allLines = reader.readAll();
        for (String[] nextLine : allLines) {
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
          colaboracionInputDto.setCantidad(nextLine[7]);

          Optional<Colaborador> posibleColaborador = this.colaboradoresRepository
              .buscarPorDocumento(Integer.parseInt(colaboradorInputDto.getNumeroDocumento()));

          if (posibleColaborador.isPresent()) {
            Colaborador colaborador = posibleColaborador.get();
            this.colaboracionesService.crearDesdeCsv(colaboracionInputDto, colaborador);
            this.colaboradoresRepository.modificar(colaborador);
          } else {
            Colaborador colaborador = this.colaboradoresService
                .crearDesdeCsv(colaboradorInputDto, this.emailSender);
            this.colaboracionesService.crearDesdeCsv(colaboracionInputDto, colaborador);
            this.colaboradoresRepository.guardar(colaborador);
          }
        }

        context.redirect("/heladeras-solidarias");
        //TODO cambiar a que te lleve a una pagina de error
      } catch (IOException | CsvException e) {
        throw new RuntimeException(e);
      }
    }

  }

  public void edit(Context context) {

  }

  public void update(Context context) {

  }

  public void delete(Context context) {

  }
}
