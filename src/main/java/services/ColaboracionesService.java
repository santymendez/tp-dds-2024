package services;

import dtos.ColaboracionInputDto;
import dtos.ColocacionHeladeraDto;
import dtos.DistribucionTarjetasDto;
import dtos.DistribucionViandasDto;
import dtos.DonacionDineroDto;
import dtos.DonacionViandasDto;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.RealizacionOfertas;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.personas.colaborador.canje.Oferta;
import models.factories.FactoryColaboracion;

/**
 * With or without youuu.
 * Clase service para la creacion de colaboraciones.
 */

public class ColaboracionesService {

  /** Crea una colaboracion a partir de un dto (para csv).
   *
   * @param dtoColaboracion input del csv para la creacion.
   */

  public Colaboracion crearDesdeCsv(ColaboracionInputDto dtoColaboracion) {
    return FactoryColaboracion.crearDesdeCsv(dtoColaboracion);
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

  /**
   * Crea una colaboracion a partir de una oferta.
   *
   * @param oferta input de realizacion de ofertas.
   * @return colaboracion.
   */

  public Colaboracion crear(Oferta oferta) {
    RealizacionOfertas realizacionOfertas = new RealizacionOfertas();
    realizacionOfertas.agregarOferta(oferta);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setRealizarOfertas(realizacionOfertas);
    colaboracion.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);

    return colaboracion;
  }

  /**
   * Crea una colaboracion a partir de una distribucion de viandas.
   *
   * @param distribucion distribucion de viandas.
   * @return colaboracion.
   */

  public Colaboracion crear(DistribucionViandas distribucion) {
    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDistribucionViandas(distribucion);
    colaboracion.setTipoColaboracion(TipoColaboracion.DISTRIBUIR_VIANDAS);

    return colaboracion;
  }

}
