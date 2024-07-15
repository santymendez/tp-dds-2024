package models.entities.colaboracion;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.canje.Oferta;

/**
 * Colaboracion para Realizar Ofertas.
 */

@Getter
@Setter
public class RealizacionOfertas {
  private List<Oferta> ofertas;
}
