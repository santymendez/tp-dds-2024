package controllers.colaboraciones;

import dtos.DonacionViandasDto;
import io.javalin.http.Context;
import java.util.Objects;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.reporte.ReporteHeladera;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.repositories.imp.TarjetasColaboradoresRepository;
import services.ColaboracionesService;
import utils.helpers.ColaboracionesHelper;
import utils.helpers.ContextHelper;
import utils.helpers.ReportesHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarViandasController.
 */

public class DonarViandasController implements InterfaceCrudViewsHandler {
  private final TarjetasColaboradoresRepository tarjetasColaboradoresRepository;
  private final GenericRepository genericRepository;
  private final ReportesHeladerasRepository reportesHeladerasRepository;
  private final ColaboracionesService colaboracionesService;

  /**
   * Constructor del Controller.
   *
   * @param tarjetasColaboradoresRepository repositorio de tarjetas de colaboradores.
   * @param genericRepository repositorio generico.
   * @param reportesHeladerasRepository repositorio de reportes.
   * @param colaboracionesService service de colaboraciones.
   */

  public DonarViandasController(
      TarjetasColaboradoresRepository tarjetasColaboradoresRepository,
      GenericRepository genericRepository,
      ReportesHeladerasRepository reportesHeladerasRepository,
      ColaboracionesService colaboracionesService
  ) {
    this.tarjetasColaboradoresRepository = tarjetasColaboradoresRepository;
    this.genericRepository = genericRepository;
    this.reportesHeladerasRepository = reportesHeladerasRepository;
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
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Optional<TarjetaColaborador> posibleTarjeta =
        this.tarjetasColaboradoresRepository.buscarPorIdColaborador(colaborador.getId());

    TarjetaColaborador tarjetaColaborador = null;

    if (posibleTarjeta.isPresent()) {
      tarjetaColaborador = posibleTarjeta.get();
    } else {
      context.redirect("/heladeras-solidarias/agregar-direccion");
      return;
    }

    DonacionViandasDto donacionViandasDto = DonacionViandasDto.fromContext(context);

    Long idHeladera =
        Long.parseLong(Objects.requireNonNull(context.formParam("heladera")));
    Heladera heladera =
        this.genericRepository.buscarPorId(idHeladera, Heladera.class).get();

    int cantViandas = Integer.parseInt(donacionViandasDto.getCantViandas());

    if (cantViandas <= heladera.consultarEspacioSobrante()) {
      Colaboracion colaboracion =
          this.colaboracionesService.crear(donacionViandasDto, heladera, colaborador);

      ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);

      ReporteHeladera reporteHeladera =
          reportesHeladerasRepository.buscarSemanalPorHeladera(heladera.getId()).get();

      //Registra las viandas donadas por
      ReportesHelper.actualizarReportePorDonacion(reporteHeladera, colaborador, cantViandas);

      this.genericRepository.modificar(heladera);
      this.genericRepository.modificar(reporteHeladera);

      //TODO broker

      context.redirect("/heladeras-solidarias?colabSucess=true");
    } else {
      int espacioDisponible = heladera.consultarEspacioSobrante();
      context.redirect("/heladeras-solidarias/colaborar?"
          + "errorDonacionViandas=true&espacioDisponible="
          + espacioDisponible);
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
