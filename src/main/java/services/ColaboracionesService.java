package services;

import dtos.ColaboracionInputDto;
import dtos.ColocacionHeladeraDto;
import dtos.DistribucionTarjetasDto;
import dtos.DistribucionViandasDto;
import dtos.DonacionDineroDto;
import dtos.DonacionViandasDto;
import dtos.RealizacionOfertasDto;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.TipoColaboracion;
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
   */

  public void crearDesdeCsv(
      ColaboracionInputDto dtoColaboracion,
      Colaborador unColaborador
  ) {
    Colaboracion unaColaboracion = FactoryColaboracion.crearDesdeCsv(dtoColaboracion);
    unaColaboracion.setColaborador(unColaborador);

    unColaborador.getColaboraciones().add(unaColaboracion);
    unColaborador.aumentarReconocimiento(unaColaboracion);
  }

  /** Crea una colaboración a partir del DTO donacionDinero.
   *
   * @param donacionDineroDto input de donacionDinero
   * @return colaborador
   */

  public Colaboracion crear(DonacionDineroDto donacionDineroDto) {
    DonacionDinero donacionDinero = new DonacionDinero();
    donacionDinero.setMontoDonado(Integer.valueOf(donacionDineroDto.getMontoDonado()));
    donacionDinero.setFrecuenciaDonacion(donacionDineroDto.getFrecuencia());

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDonacionDinero(donacionDinero);
    colaboracion.setTipoColaboracion(TipoColaboracion.DONAR_DINERO);

    return colaboracion;
  }

  public Colaboracion crear(DonacionViandasDto donacionViandasDto) {
    return null;
  }

  public Colaboracion crear(DistribucionViandasDto distribucionViandasDto) {
    return null;
  }

  public Colaboracion crear(DistribucionTarjetasDto distribucionTarjetasDto) {
    return null;
  }

  public Colaboracion crear(ColocacionHeladeraDto colocacionHeladeraDto) {
    return null;
  }

  public Colaboracion crear(RealizacionOfertasDto distribucionTarjetasDto) {
    return null;
  }

}
