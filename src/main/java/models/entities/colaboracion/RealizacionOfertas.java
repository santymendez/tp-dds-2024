package models.entities.colaboracion;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.canje.Oferta;

/**
 * Colaboracion para Realizar Ofertas.
 */

@Getter
@Setter
@Embeddable
public class RealizacionOfertas {
  @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
  @JoinColumn(name = "oferta_id", referencedColumnName = "id")
  private List<Oferta> ofertas;
}
