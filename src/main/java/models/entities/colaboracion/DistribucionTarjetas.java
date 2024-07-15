package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

/**
 * Clase la cual se encarga de la colaboración distribuir tarjetas.
 */

@Setter
@Getter
public class DistribucionTarjetas {
  private List<TarjetaVulnerable> tarjetasEntregadas;
  private Integer cantTarjetasEntregadas;

  public DistribucionTarjetas() {
    this.tarjetasEntregadas = new ArrayList<>();
  }
}
