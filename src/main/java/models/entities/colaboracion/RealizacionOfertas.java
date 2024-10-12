package models.entities.colaboracion;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "colaboracion_id", referencedColumnName = "id")
  private Oferta oferta;

  public void agregarOferta(Oferta oferta) {
    this.oferta = oferta;
  }

}
