package utils.helpers;

import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ViandasPorColaborador;

/**
 * Agrupa logica comun cuando instanciamos y guardamos una colaboracion.
 */

public class ReportesHelper {

  /**
   * Permite resgistrar colaboraciones en las que se agreguen cantViandas en el reporte.
   * Busca el colaborador en la lista de ViandasXColaborador, si lo encuentra le agrega
   * las cantViandas que dono, si no crea un nuevo ViandasXColaborador y lo agrega a la lista.
   *
   * @param colaborador Colaborador que realiza la donación.
   * @param cantViandas Cantidad de cantViandas donadas.
   */

  public static void actualizarReportePorDonacion(
      ReporteHeladera reporte,
      Colaborador colaborador,
      Integer cantViandas
  ) {

    Optional<ViandasPorColaborador> posibleViandasPorColaborador =
        reporte.buscarPorColaborador(colaborador);

    ViandasPorColaborador viandasPorColaborador;

    if (posibleViandasPorColaborador.isPresent()) {
      viandasPorColaborador = posibleViandasPorColaborador.get();
      viandasPorColaborador.agregarViandas(cantViandas);
    } else {
      viandasPorColaborador = new ViandasPorColaborador(colaborador, cantViandas);
      reporte.agregarNuevaColaboracion(viandasPorColaborador);
    }

    for (int i = 0; i < cantViandas; i++) {
      reporte.viandaColocada();
    }
  }

  /**
   * Actualiza los reportes de heladeras por distribucion de viandas.
   *
   * @param reporteOrigen reporte de la heladera de origen.
   * @param reporteDestino reporte de la heladera de destino.
   * @param colaborador colaborador el cual lo hace.
   * @param cantViandas cantidad de viandas.
   */

  public static void actualizarReportePorDistribucion(
      ReporteHeladera reporteOrigen, ReporteHeladera reporteDestino,
      Colaborador colaborador, Integer cantViandas) {

    for (int i = 0; i < cantViandas; i++) {
      reporteOrigen.viandaRetirada();
    }

    ReportesHelper.actualizarReportePorDonacion(reporteDestino, colaborador, cantViandas);
  }
}
