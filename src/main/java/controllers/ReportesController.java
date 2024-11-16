package controllers;

import io.javalin.http.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.reporte.ReporteSemanal;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesSemanalesRepository;
import utils.helpers.DownloaderHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para la visualizacion de Reportes.
 */

public class ReportesController implements InterfaceCrudViewsHandler {
  private final GenericRepository genericRepository;
  private final ReportesSemanalesRepository reportesSemanalesRepository;

  public ReportesController(
      GenericRepository genericRepository,
      ReportesSemanalesRepository reportesSemanalesRepository
  ) {
    this.genericRepository = genericRepository;
    this.reportesSemanalesRepository = reportesSemanalesRepository;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Reportes");
    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    List<ReporteSemanal> reportes = this.genericRepository.buscarTodos(ReporteSemanal.class);
    reportes.sort(Comparator.comparing(ReporteSemanal::getFecha).reversed());
    reportes = reportes.stream().limit(5).toList();

    model.put("reportes", reportes);

    context.render("reportes.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    LocalDate fechaInicial = 
        LocalDate.parse(Objects.requireNonNull(context.formParam("fechaInicial")));
    LocalDate fechaFinal =
        LocalDate.parse(Objects.requireNonNull(context.formParam("fechaFinal")));

    List<ReporteSemanal> reportes =
        this.reportesSemanalesRepository.buscarTodosPorRangoDeFecha(fechaInicial, fechaFinal);

    if (reportes.isEmpty()) {
      context.redirect("/heladeras-solidarias/reportes?emptyDates=true");
      return;
    }

    List<String> paths = reportes.stream().map(ReporteSemanal::getPath).toList();

    File zipFile = DownloaderHelper.zip(paths);

    context.contentType("application/octet-stream");
    context.header("Content-Disposition", "attachment; filename=" + zipFile.getName());

    try {
      context.result(new FileInputStream(zipFile));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    zipFile.delete();
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
