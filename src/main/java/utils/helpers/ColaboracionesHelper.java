package utils.helpers;

import config.RepositoryLocator;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ViandasPorColaborador;
import models.repositories.imp.GenericRepository;


/**
 *  Agrupa logica comun cuando instanciamos y guardamos una colaboracion.
 */

public class ColaboracionesHelper {

  /**
   * Helper method para la realizacion de una colaboracion.
   *
   * @param colaboracion una colaboracion.
   * @param colaborador un colaborador.
   */

  public static void realizarColaboracion(Colaboracion colaboracion, Colaborador colaborador) {
    colaboracion.setColaborador(colaborador);

    colaborador.agregarColaboracion(colaboracion);

    if (colaborador.getTipoColaborador() == TipoColaborador.FISICO) {
      colaborador.aumentarReconocimiento(colaboracion);
    }

    GenericRepository repository = RepositoryLocator.instanceOf(GenericRepository.class);
    repository.modificar(colaborador);
  }

  /**
   * Permite resgistrar colaboraciones en las que se agreguen viandas en el reporte.
   * Busca el colaborador en la lista de ViandasXColaborador, si lo encuentra le agrega
   * las viandas que dono, si no crea un nuevo ViandasXColaborador y lo agrega a la lista.
   *
   * @param colaborador Colaborador que realiza la donación.
   * @param viandas Cantidad de viandas donadas.
   */

  public static void colaboracionRealizada(ReporteHeladera reporte,
                                    Colaborador colaborador, Integer viandas) {

    Optional<ViandasPorColaborador> viandasPorColaborador =
        reporte.buscarPorColaborador(colaborador);

    if (viandasPorColaborador.isPresent()) {
      viandasPorColaborador.get().agregarViandas(viandas);
    } else {
      ViandasPorColaborador nuevo = new ViandasPorColaborador(colaborador, viandas);
      reporte.getViandasPorColaboradores().add(nuevo);
    }
  }
}
