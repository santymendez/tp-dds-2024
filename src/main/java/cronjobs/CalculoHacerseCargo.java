package cronjobs;

import config.RepositoryLocator;
import java.util.List;
import models.db.PersistenceUnitSwitcher;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboradoresRepository;


/**
 *  Clase para calcular mensualmente los puntos para los colaboradores que se hacen cargo de
 *  una heladera.
 */

public class CalculoHacerseCargo {

  /**
   * Main para el conjob.
   *
   * @param args argumentos que no se usan.
   */

  public static void main(String[] args) {

    PersistenceUnitSwitcher.switchPersistenceUnit("cronjob-persistence-unit");

    ColaboradoresRepository colaboradoresRepository = RepositoryLocator
        .instanceOf(ColaboradoresRepository.class);

    List<Colaborador> colaboradores = colaboradoresRepository.buscarTodosCon1MesActivoMinimo();

    for (Colaborador colaborador : colaboradores) {

      List<Colaboracion> colaboraciones = colaborador.getColaboraciones()
          .stream().filter(c -> c.getTipoColaboracion() == TipoColaboracion.HACERSE_CARGO_HELADERA)
          .toList();

      for (Colaboracion colaboracion : colaboraciones) {
        colaboracion.getHacerseCargoHeladera().getHeladeraColocada().getEstadosHeladera().sort(
            (e1, e2) -> e1.getFechaInicial().compareTo(e2.getFechaInicial()));

        colaborador.getReconocimiento().sumarPuntos(colaboracion);

        System.out.println(colaborador.getReconocimiento().getPuntosPorColaborar());
      }

      colaboradoresRepository.modificar(colaborador);
    }

  }
}
