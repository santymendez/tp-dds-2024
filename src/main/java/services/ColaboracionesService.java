package services;

import dtos.ColaboracionInputDto;
import dtos.ColocacionHeladeraDto;
import dtos.DistribucionViandasDto;
import dtos.DonacionDineroDto;
import dtos.DonacionViandasDto;
import dtos.OfertaInputDto;
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

  public Colaboracion crear(
      DonacionViandasDto donacionViandasDto,
      Heladera heladera,
      Colaborador colaborador
  ) {
    DonacionViandas donacionViandas = new DonacionViandas();
    int cantViandas = Integer.parseInt(donacionViandasDto.getCantViandas());
    donacionViandas.setCantViandas(cantViandas);

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
      DistribucionViandasDto distribucionDto,
      Heladera heladeraOrigen,
      Heladera heladeraDestino
  ) {
    DistribucionViandas distribucionViandas = new DistribucionViandas();
    distribucionViandas.setMotivoDistribucion(distribucionDto.getMotivoDistribucion());
    distribucionViandas.setCantViandasDistribuidas(
        Integer.parseInt(distribucionDto.getCantViandasDistribuidas())
    );
    distribucionViandas.setHeladeraOrigen(heladeraOrigen);
    distribucionViandas.setHeladeraDestino(heladeraDestino);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setDistribucionViandas(distribucionViandas);
    colaboracion.setTipoColaboracion(TipoColaboracion.DISTRIBUIR_VIANDAS);

    return colaboracion;
  }

  public Colaboracion crear(Integer cantidadTarjetas) {
    return null;
  }

  public Colaboracion crear(ColocacionHeladeraDto colocacionHeladeraDto) {
    return null;
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
    oferta.setPuntosNecesarios(Float.parseFloat(ofertaInputDto.getPuntosNecesarios()));
    oferta.setImagenIlustrativa(ofertaInputDto.getImagenIlustrativa());
    oferta.setDescripcion(ofertaInputDto.getDescripcion());
    oferta.setOfertante(colaborador);

    RealizacionOfertas realizacionOfertas = new RealizacionOfertas();
    realizacionOfertas.setOferta(oferta);

    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setOfertaRealizada(realizacionOfertas);
    colaboracion.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);

    return colaboracion;
  }

}
