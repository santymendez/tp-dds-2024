package services;

import dtos.ColaboracionInputDto;
import dtos.ColocacionHeladeraDto;
import dtos.DistribucionTarjetasDto;
import dtos.DistribucionViandasDto;
import dtos.DonacionDineroDto;
import dtos.DonacionViandasDto;
import java.time.LocalDate;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.DonacionViandas;
import models.entities.colaboracion.RealizacionOfertas;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Comida;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.factories.FactoryColaboracion;
import models.repositories.imp.GenericRepository;

/**
 * With or without youuu.
 * Clase service para la creacion de colaboraciones.
 */

public class ColaboracionesService {

  private final GenericRepository genericRepository;

  public ColaboracionesService(
      GenericRepository genericRepository
  ) {
    this.genericRepository = genericRepository;
  }

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

  /**
   * Crea una colaboracion a para el caso de donacion de viandas.
   *
   * @param donacionViandasDto dto de la donacion.
   * @param heladera la heladera.
   * @param colaborador colaborador.
   * @return una colaboracion.
   */

  public Colaboracion crear(DonacionViandasDto donacionViandasDto,
                            Heladera heladera, Colaborador colaborador) {
    DonacionViandas donacionViandas = new DonacionViandas();
    int cantViandas = Integer.parseInt(donacionViandasDto.getCantViandas());

    Comida comida = new Comida(
        donacionViandasDto.getNombreComida(),
        LocalDate.parse(donacionViandasDto.getFechaVencimiento())
    );

    for (int i = 0; i < cantViandas; i++) {
      Vianda vianda = new Vianda(
          comida,
          LocalDate.now(),
          colaborador,
          heladera,
          Integer.parseInt(donacionViandasDto.getCalorias()),
          Float.parseFloat(donacionViandasDto.getPeso()),
          false
      );

      this.genericRepository.guardar(vianda);

      donacionViandas.getViandasDonadas().add(vianda);
    }

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDonacionViandas(donacionViandas);
    colaboracion.setTipoColaboracion(TipoColaboracion.DONAR_VIANDA);
    
    return colaboracion;
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
