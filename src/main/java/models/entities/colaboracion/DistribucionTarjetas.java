package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
  @OneToMany(cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "tarjeta_id", referencedColumnName = "id")
  private List<TarjetaVulnerable> tarjetasEntregadas;

  @Column(name = "cantidadTarjetasEntregadas")
  private Integer cantTarjetasEntregadas;

  public DistribucionTarjetas() {
    this.tarjetasEntregadas = new ArrayList<>();
  }
}
