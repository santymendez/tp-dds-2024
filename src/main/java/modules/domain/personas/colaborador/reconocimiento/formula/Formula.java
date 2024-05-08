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
  private Float coefViandasDonadas;
  private Float coefViandasDistribuidas;
  private Float coefTarjetasRepartidas;
  private Float coefHeladerasActivas;

  /** Calcula los puntos de cada colaboracion que recibe como parametro.
   *
   * @param colaboracion es la colaboracion a la que se le calculara los puntos correspondientes
   * @return Float
   */

  // TODO: Corregir
  public Float calcularPuntosDe(Colaboracion colaboracion) {
    switch (colaboracion.getTipoColaboracion()) {
      case DONAR_DINERO -> {
        return colaboracion.getMonto() * this.coefPesosDonados;
      }
      case DONAR_VIANDA -> {
        return colaboracion.getViandas().size() * this.coefViandasDonadas;
      }
      case DISTRIBUIR_VIANDA -> {
        return colaboracion.getCantViandasDistribuidas() * this.coefViandasDistribuidas;
      }
      case ENTREGAR_TARJETA -> {
        return this.coefTarjetasRepartidas;
      }
      case COLOCAR_HELADERA -> {
        return colaboracion.getHeladera().mesesActiva() * this.getCoefHeladerasActivas();
      }
      default -> {
        return 0f;
      }
    }
  }
}
