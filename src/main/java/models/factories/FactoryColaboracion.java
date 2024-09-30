package models.factories;

import dtos.ColaboracionInputDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionTarjetas;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.DonacionViandas;
import models.entities.colaboracion.TipoColaboracion;

/**
 * Representa un Factory para las colaboraciones.
 */

public class FactoryColaboracion {

  /**
   * Crea una colaboracion a partir de un input Colaboracion.
   *
   * @param colaboracionInputDto El input para crear la colaboracion.
   * @return Una instancia de una Colaboracion.
   */

  public static Colaboracion crearCon(ColaboracionInputDto colaboracionInputDto) {
    Colaboracion unaColaboracion = new Colaboracion();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    unaColaboracion
        .setFechaColaboracion(LocalDate.parse(colaboracionInputDto.getFecha(), formatter));

    unaColaboracion.setTipoColaboracion(TipoColaboracion
        .valueOf(colaboracionInputDto.getTipoColaboracion()));

    Integer cantidad = Integer.parseInt(colaboracionInputDto.getCantidad());

    switch (unaColaboracion.getTipoColaboracion()) {
      case DONAR_DINERO -> {
        unaColaboracion.setDonacionDinero(new DonacionDinero());
        unaColaboracion.getDonacionDinero().setMontoDonado(cantidad);
      }
      case DONAR_VIANDA -> {
        unaColaboracion.setDonacionViandas(new DonacionViandas());
        unaColaboracion.getDonacionViandas().setCantViandas(cantidad);
      }
      case DISTRIBUIR_VIANDAS -> {
        unaColaboracion.setDistribucionViandas(new DistribucionViandas());
        unaColaboracion.getDistribucionViandas().setCantViandasDistribuidas(cantidad);
      }
      case ENTREGAR_TARJETA -> {
        unaColaboracion.setDistribucionTarjetas(new DistribucionTarjetas());
        unaColaboracion.getDistribucionTarjetas().setCantTarjetasEntregadas(cantidad);
      }
      default -> throw new RuntimeException("No existe esa forma de colaborar");
    }

    return unaColaboracion;
  }
}
