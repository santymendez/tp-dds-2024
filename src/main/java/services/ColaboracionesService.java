package services;

import dtos.ColaboracionInputDto;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.factories.FactoryColaboracion;

/**
 * With or without youuu.
 * Clase service para la creacion de colaboraciones.
 */

public class ColaboracionesService {

  /** Crea una colaboracion a partir de un dto (para csv).
   *
   * @param dtoColaboracion input del csv para la creacion.
   * @param unColaborador colaborador cargado desde el controller del csv.
   * @return colaboracion del csv.
   */

  public Colaboracion crearDesdeCsv(
      ColaboracionInputDto dtoColaboracion,
      Colaborador unColaborador
  ) {
    Colaboracion unaColaboracion = FactoryColaboracion.crearCon(dtoColaboracion);
    unaColaboracion.setColaborador(unColaborador);

    unColaborador.getColaboraciones().add(unaColaboracion);
    unColaborador.aumentarReconocimiento(unaColaboracion);

    return unaColaboracion;
  }

  //TODO completar (falta el colaborador y para eso necesitamos sesiones)
  public Colaboracion crear(ColaboracionInputDto dtoColaboracion) {
    return FactoryColaboracion.crearCon(dtoColaboracion);
  }
}
