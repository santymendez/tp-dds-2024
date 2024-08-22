package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

/**
 * Clase la cual se encarga de la colaboración distribuir tarjetas.
 */

@Setter
@Getter
@Embeddable
public class DistribucionTarjetas {
  @Transient //TODO hacer relacion
  private List<TarjetaVulnerable> tarjetasEntregadas;
  @Column(name = "cantidadTarjetasEntregadas")
  private Integer cantTarjetasEntregadas;

  public DistribucionTarjetas() {
    this.tarjetasEntregadas = new ArrayList<>();
  }
}
