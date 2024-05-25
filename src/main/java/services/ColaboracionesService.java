package services;

import dtos.ColaboracionInputDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;

/**
 * Instancia la colaboracion y la guarda en su repositorio correspondiente.
 */

public class ColaboracionesService {

  /**
   * Se crea una colaboracion a partir del input del csv.
   *
   * @param colaboracionInputDto Input de la colaboracion.
   */

  public Colaboracion crear(ColaboracionInputDto colaboracionInputDto) {
    Colaboracion unaColaboracion = new Colaboracion();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    unaColaboracion.setFechaDonacion(LocalDate.parse(colaboracionInputDto.getFecha(), formatter));
    unaColaboracion.setTipoColaboracion(TipoColaboracion
                                    .valueOf(colaboracionInputDto.getTipoColaboracion()));
    Integer cantidad = colaboracionInputDto.getCantidad();

    switch (unaColaboracion.getTipoColaboracion()) {
      case DONAR_DINERO -> unaColaboracion.setMonto(cantidad);
      case DONAR_VIANDA -> unaColaboracion.setCantViandas(cantidad);
      case DISTRIBUIR_VIANDAS -> unaColaboracion.setCantViandasDistribuidas(cantidad);
      case ENTREGAR_TARJETA -> unaColaboracion.setCantTarjetasEntregadas(cantidad);
      default -> throw new RuntimeException("No existe esa forma de colaborar");
    }
    return unaColaboracion;
  }
}
