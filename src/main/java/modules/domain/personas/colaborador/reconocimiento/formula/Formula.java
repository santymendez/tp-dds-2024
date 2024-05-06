package modules.domain.personas.colaborador.reconocimiento.formula;

import lombok.Getter;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;

/**
 * Representa una formula de calculo de puntos.
 */

@Getter
@Setter
public class Formula {

  private Float coefPesosDonados;
  private Float coefDonarVianda;

  /** Calcula los puntos de cada colaboracion que recibe como parametro.
   *
   * @param colaboracion es la colaboracion a la que se le calculara los puntos correspondientes
   * @return Float
   */

  //TODO
  public Float calcularPuntosDe(Colaboracion colaboracion) {
    switch (colaboracion.getTipoColaboracion()) {
      case DONAR_DINERO -> {
        Integer monto = colaboracion.getMonto();
        return monto * this.coefPesosDonados;
      }
      case DONAR_VIANDA -> {
        return this.coefDonarVianda;
      }
      default -> {
        return 0f;
      }
    }
  }
}
