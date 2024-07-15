package models.entities.searchers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.Colaborador;

/**
 * Busca las heladeras que un Colaborador más frecuenta según una lista de colaboraciones.
 */

@Getter
@Setter
public class BuscadorHeladerasFrecuentes {
  private Integer cantidadRequerida;

  /**
   * Busca las heladeras más frecuentes para un Colaborador dada una lista de Colaboraciones.
   *
   * @param colaborador El Colaborador que frecuenta las heladeras.
   * @return Lista de Heladeras frecuentes.
   */

  public List<Heladera> heladerasFrecuentes(
      Colaborador colaborador
  ) {
    Stream<Colaboracion> distribucionesDeViandas = colaborador.getColaboraciones().stream()
        .filter(colab -> colab.getTipoColaboracion().equals(TipoColaboracion.DISTRIBUIR_VIANDAS));

    Stream<Colaboracion> donacionesDeViandas = colaborador.getColaboraciones().stream()
        .filter(colab -> colab.getTipoColaboracion().equals(TipoColaboracion.DONAR_VIANDA));

    Stream<Colaboracion> colaboracionesOrdenadas =
        Stream.concat(distribucionesDeViandas, donacionesDeViandas)
            .sorted(Comparator.comparing(Colaboracion::getFechaColaboracion));

    return this.obtenerHeladerasRequeridas(colaboracionesOrdenadas.toList());
  }

  /**
   * Crea una lista de Heladeras a partir de una lista de Colaboraciones.
   *
   * @param colaboraciones Lista de Colaboraciones.
   * @return Lista de Heladeras.
   */

  public List<Heladera> obtenerHeladerasRequeridas(List<Colaboracion> colaboraciones) {
    List<Heladera> heladeras = new ArrayList<>();

    for (Colaboracion colaboracion : colaboraciones) {
      switch (colaboracion.getTipoColaboracion()) {
        case DISTRIBUIR_VIANDAS -> {
          if (colaboracion
              .getDistribucionViandas()
              .getHeladeraDestino()
              .getModAlmacenamiento()
              .consultarEspacioSobrante() != 0) {
            heladeras.add(colaboracion.getDistribucionViandas().getHeladeraDestino());
          }
        }
        case DONAR_VIANDA -> {
          for (Vianda vianda : colaboracion.getDonacionViandas().getViandas()) {
            if (vianda.getHeladera().getModAlmacenamiento().consultarEspacioSobrante() != 0) {
              heladeras.add(vianda.getHeladera());
            } else {
              break;
            }
          }
        }
        default -> throw new RuntimeException("Hay una Colaboración no valida");
      }
    }
    return heladeras.stream().limit(this.cantidadRequerida).toList();
  }
}
