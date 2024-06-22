package models.entities.searchers;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.imp.TecnicosRepository;

/**
 * Busca tecnicos cercanos a una heladera en particular.
 */

public class BuscadorTecnicosCercanos {
  private final TecnicosRepository tecnicosRepository;

  public BuscadorTecnicosCercanos(TecnicosRepository tecnicosRepository) {
    this.tecnicosRepository = tecnicosRepository;
  }

  /**
   * Busca tecnicos cercanos a una heladera.
   *
   * @param heladera Heladera tomada como parametro de busqueda.
   * @return Lista de tecnicos cercanos a la heladera indicada.
   */

  public List<Tecnico> buscarTecnicosCercanosA(Heladera heladera) {
    String nombreCiudad = heladera.getDireccion().getProvincia().getCiudad().getNombreCiudad();
    return tecnicosRepository.buscar(nombreCiudad);
  }
}
