package controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import dtos.ColaboracionInputDto;
import dtos.ColaboradorInputDto;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.contacto.TipoContacto;
import models.repositories.imp.ColaboracionesRepository;
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
  private final ColaboracionesRepository colaboracionesRepository;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor para el CSV Controller.
   *
   * @param colaboradoresService Es el Service de los Colaboradores.
   */

  public CsvController2(
      ColaboradoresService colaboradoresService,
      ColaboradoresRepository colaboradoresRepository,
      ColaboracionesRepository colaboracionesRepository,
      ColaboracionesService colaboracionesService,
      EmailSender emailSender
  ) {
    this.colaboradoresService = colaboradoresService;
    this.colaboradoresRepository = colaboradoresRepository;
    this.colaboracionesRepository = colaboracionesRepository;
    this.emailSender = emailSender;
    this.colaboracionesService = colaboracionesService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
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

          Colaborador colaborador = this.colaboradoresService
              .crearDesdeCsv(colaboradorInputDto, this.emailSender);
          Colaboracion colaboracion = this.colaboracionesService
              .crearDesdeCsv(colaboracionInputDto, colaborador);
          this.colaboradoresRepository.guardar(colaborador);
          this.colaboracionesRepository.guardar(colaboracion);
        }
        context.redirect("/heladeras-solidarias");
        //TODO cambiar a que te lleve a una pagina de error
      } catch (IOException | CsvException e) {
        throw new RuntimeException(e);
      }
    }

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
