package services;

import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Canje;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.GenericRepository;

/**
 * Servicio para canjear puntos.
 */

public class CanjearPuntosService {
  private final GenericRepository genericRepository;

  public CanjearPuntosService(GenericRepository genericRepository) {
    this.genericRepository = genericRepository;
  }

  /**
   * Crea un canje.
   *
   * @param oferta la oferta.
   * @param colaborador el colaborador.
   */

  public void crear(Oferta oferta, Colaborador colaborador) {
    colaborador.getReconocimiento().restarPuntos(oferta.getPuntosNecesarios());
    this.genericRepository.modificar(colaborador);

    Canje canje = new Canje(colaborador, oferta);
    this.genericRepository.guardar(canje);
  }
}
