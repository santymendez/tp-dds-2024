package services;

import dtos.ColaboracionInputDto;
import dtos.DistribucionViandasInputDto;
import dtos.DonacionDineroInputDto;
import dtos.DonacionViandasInputDto;
import dtos.OfertaInputDto;
import java.time.LocalDate;
import java.util.List;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionTarjetas;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.DonacionViandas;
import models.entities.colaboracion.HacerseCargoHeladera;
import models.entities.colaboracion.RealizacionOferta;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Comida;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.Colaborador;
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
   * @param donacionDineroInputDto input de donacionDinero
   * @return colaborador
   */

  public Colaboracion crear(DonacionDineroInputDto donacionDineroInputDto) {
    DonacionDinero donacionDinero = new DonacionDinero();
    donacionDinero.setMontoDonado(donacionDineroInputDto.getMontoDonado());
    donacionDinero.setFrecuenciaDonacion(donacionDineroInputDto.getFrecuencia());

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDonacionDinero(donacionDinero);
    colaboracion.setTipoColaboracion(TipoColaboracion.DONAR_DINERO);

    return colaboracion;
  }

  /**
   * Crea una colaboracion a para el caso de donacion de viandas.
   *
   * @param donacionViandasInputDto dto de la donacion.
   * @param heladera la heladera.
   * @param colaborador colaborador.
   * @return una colaboracion.
   */

  public Colaboracion crear(
      DonacionViandasInputDto donacionViandasInputDto,
      Heladera heladera,
      Colaborador colaborador
  ) {
    DonacionViandas donacionViandas = new DonacionViandas();
    int cantViandas = donacionViandasInputDto.getCantViandas();
    donacionViandas.setCantViandas(cantViandas);

    Comida comida = new Comida(
        donacionViandasInputDto.getNombreComida(),
        donacionViandasInputDto.getFechaVencimiento()
    );

    for (int i = 0; i < cantViandas; i++) {
      Vianda vianda = new Vianda(
          comida,
          LocalDate.now(),
          colaborador,
          heladera,
          donacionViandasInputDto.getCalorias(),
          donacionViandasInputDto.getPeso(),
          true
      );

      donacionViandas.getViandasDonadas().add(vianda);
    }

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDonacionViandas(donacionViandas);
    colaboracion.setTipoColaboracion(TipoColaboracion.DONAR_VIANDA);

    return colaboracion;
  }

  /**
   * Crea una colaboracion a partir de una distribucion de viandas.
   *
   * @param distribucionDto dto de la distribucion.
   * @param heladeraOrigen heladera de origen.
   * @param heladeraDestino heladera de destino.
   * @return colaboracion.
   */

  public Colaboracion crear(
      DistribucionViandasInputDto distribucionDto,
      Heladera heladeraOrigen,
      Heladera heladeraDestino
  ) {
    DistribucionViandas distribucionViandas = new DistribucionViandas();
    distribucionViandas.setMotivoDistribucion(distribucionDto.getMotivoDistribucion());
    distribucionViandas.setCantViandasDistribuidas(distribucionDto.getCantViandasDistribuidas());
    distribucionViandas.setHeladeraOrigen(heladeraOrigen);
    distribucionViandas.setHeladeraDestino(heladeraDestino);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDistribucionViandas(distribucionViandas);
    colaboracion.setTipoColaboracion(TipoColaboracion.DISTRIBUIR_VIANDAS);

    return colaboracion;
  }

  /**
   * Crea una colaboracion a partir de una distribucion de tarjetas.
   *
   * @param cantidadTarjetas la cantidad de tarjetas a distribuir
   * @return una colaboración.
   */

  public Colaboracion crear(Integer cantidadTarjetas) {
    DistribucionTarjetas distribucionTarjetas = new DistribucionTarjetas();
    distribucionTarjetas.setCantTarjetasEntregadas(cantidadTarjetas);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDistribucionTarjetas(distribucionTarjetas);
    colaboracion.setTipoColaboracion(TipoColaboracion.ENTREGAR_TARJETA);

    return colaboracion;
  }

  /**
   * Para hacerse cargo de una heladera.
   *
   * @param heladeraApadrinada la heladera a hacerse cargo.
   * @param direccion la direccion.
   * @param colaborador colaborador que se hace cargo.
   * @return colaboracion.
   */

  public Colaboracion crear(Heladera heladeraApadrinada,
                            Direccion direccion, Colaborador colaborador) {

    heladeraApadrinada.setDireccion(direccion);

    HacerseCargoHeladera hacerseCargoHeladera = new HacerseCargoHeladera();
    hacerseCargoHeladera.setHeladeraColocada(heladeraApadrinada);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setColaborador(colaborador);
    colaboracion.setFechaColaboracion(LocalDate.now());
    colaboracion.setTipoColaboracion(TipoColaboracion.HACERSE_CARGO_HELADERA);
    colaboracion.setHacerseCargoHeladera(hacerseCargoHeladera);

    return colaboracion;
  }

  /**
   * Crea una colaboracion a partir de una oferta.
   *
   * @param ofertaInputDto dto de la oferta.
   * @param colaborador colaborador.
   * @return colaboracion.
   */

  public Colaboracion crear(OfertaInputDto ofertaInputDto, Colaborador colaborador) {
    Oferta oferta = new Oferta();
    oferta.setNombre(ofertaInputDto.getNombre());
    oferta.setPuntosNecesarios(ofertaInputDto.getPuntosNecesarios());
    oferta.setImagenIlustrativa(ofertaInputDto.getImagenIlustrativa());
    oferta.setDescripcion(ofertaInputDto.getDescripcion());
    oferta.setOfertante(colaborador);

    RealizacionOferta realizacionOferta = new RealizacionOferta();
    realizacionOferta.setOfertaRealizada(oferta);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setOfertaRealizada(realizacionOferta);
    colaboracion.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);

    return colaboracion;
  }

}
