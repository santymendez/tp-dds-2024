package controllers;

import dtos.ColaboracionInputDto;
import dtos.ColaboradorInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.colaboracion.Colaboracion;
import services.ColaboracionesService;
import services.ColaboradoresService;
import utils.helpers.UploadedFilesHelper;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.sender.channels.EmailSender;

/**
 * Csv controller 2.
 */

public class CsvController implements InterfaceCrudViewsHandler {

  private final EmailSender emailSender;
  private final ColaboradoresService colaboradoresService;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor para el CSV Controller.
   *
   * @param colaboradoresService Es el Service de los Colaboradores.
   */

  public CsvController(
      ColaboradoresService colaboradoresService,
      ColaboracionesService colaboracionesService,
      EmailSender emailSender
  ) {
    this.colaboradoresService = colaboradoresService;
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
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("cargar-csv.hbs", model);
  }

  /**
   * Guarda los datos del archivo CSV en la base de datos.
   *
   * @param context el contexto de la aplicación.
   */

  public void save(Context context) {
    List<String[]> allLines = UploadedFilesHelper.getCsvFromContext(context);

    if (allLines != null) {
      for (String[] nextLine : allLines) {
        ColaboradorInputDto colaboradorInputDto = ColaboradorInputDto.fromCsv(nextLine);
        ColaboracionInputDto colaboracionInputDto = ColaboracionInputDto.fromCsv(nextLine);

        Colaboracion colaboracion =
            this.colaboracionesService.crearDesdeCsv(colaboracionInputDto);

        this.colaboradoresService
            .crearDesdeCsv(colaboradorInputDto, this.emailSender, colaboracion);
      }

      context.redirect("/heladeras-solidarias");
    } else {
      context.redirect("/heladeras-solidarias/cargar-csv?invalidFile=true");
    }
  }

  public void edit(Context context) {

  }

  public void update(Context context) {

  }

  public void delete(Context context) {

  }
}
