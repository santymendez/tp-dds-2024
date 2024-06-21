package models.entities.searchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Busca las heladeras que un Colaborador mas frecuenta segun una lista de colaboraciones.
 */

@Getter
@Setter
public class BuscadorHeladerasFrecuentes {
  private Integer cantidadRequerida;

  /**
   * Busca las heladeras mas frecuentes para un Colaborador dada una lista de Colaboraciones.
   *
   * @param colaborador El Colaborador que frecuenta las heladeras.
   * @param colaboraciones Las colaboraciones realizadas.
   * @return Lista de Heladeras frecuentes.
   */

  public List<Heladera> heladerasFrecuentes(
      Colaborador colaborador,
      List<Colaboracion> colaboraciones
  ) {
    Stream<Colaboracion> distribucionesDeViandas = colaboraciones.stream()
        .filter(colab -> colab.getHeladeraDestino() != null);
    Stream<Colaboracion> donacionesDeViandas = colaboraciones.stream()
        .filter(colaboracion -> !colaboracion.getViandas().isEmpty());

    Stream<Colaboracion> colaboracionesConHeladeras =
        Stream.concat(distribucionesDeViandas, donacionesDeViandas);
  }
}
