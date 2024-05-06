package modules.domain.personas.colaborador.reconocimiento.formula;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;

/**
 * Representa una formula de calculo de puntos.
 */
@Getter
@Setter
public class Formula {

  public void decodificarColaboraciones(List<Colaboracion> colaboraciones) {
  }

  public Float calcular(List<Colaboracion> colaboraciones) {
    return 1.0f;
  }
}
