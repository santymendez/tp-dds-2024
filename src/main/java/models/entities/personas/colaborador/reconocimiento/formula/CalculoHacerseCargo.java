package models.entities.personas.colaborador.reconocimiento.formula;

import config.RepositoryLocator;
import java.util.List;
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

    List<Colaborador> colaboradores = RepositoryLocator
        .instanceOf(ColaboradoresRepository.class).buscarTodos();

    for (Colaborador colaborador : colaboradores) {

      List<Colaboracion> colaboraciones = colaborador.getColaboraciones()
          .stream().filter(c -> c.getTipoColaboracion() == TipoColaboracion.HACERSE_CARGO_HELADERA)
          .toList();

      for (Colaboracion colaboracion : colaboraciones) {
        colaborador.getReconocimiento().sumarPuntos(colaboracion);
      }
    }

  }
}
