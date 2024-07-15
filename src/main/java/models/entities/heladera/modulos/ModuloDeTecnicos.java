package models.entities.heladera.modulos;

import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.searchers.BuscadorTecnicosCercanos;

/**
 * Representa al modulo de la heladera que busca a los tecnicos
 * cercanos en casa de alguna falla tecnica.
 */

@Setter
public class ModuloDeTecnicos {
  private BuscadorTecnicosCercanos buscadorTecnicosCercanos;

  public ModuloDeTecnicos() {}

  public void notificarTecnicos(Heladera heladera) {
    buscadorTecnicosCercanos.buscarTecnicosCercanosA(heladera);
  }
}
