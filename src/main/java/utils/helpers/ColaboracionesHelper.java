package utils.helpers;

import config.RepositoryLocator;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
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

    if (colaborador.getTipoColaborador() == TipoColaborador.FISICO && colaboracion.noEsParcial()) {
      colaborador.aumentarReconocimiento(colaboracion);
    }
    //En el caso de las parciales se aumenta el reconocimiento al terminar la colaboracion

    GenericRepository repository = RepositoryLocator.instanceOf(GenericRepository.class);
    repository.modificar(colaborador);
    repository.guardar(colaboracion);
  }
}
