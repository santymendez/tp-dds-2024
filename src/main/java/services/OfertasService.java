package services;

import dtos.OfertaInputDto;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Oferta;

/**
 * Service para las ofertas.
 */

public class OfertasService {

  /**
   * Crea una oferta a partir de un DTO.
   *
   * @param ofertaInputDto el DTO con los datos del producto/servicio.
   * @return la oferta creada.
   */

  public Oferta crear(OfertaInputDto ofertaInputDto, Colaborador colaborador) {
    Oferta oferta = new Oferta();
    oferta.setNombre(ofertaInputDto.getNombre());
    oferta.setPuntosNecesarios(Float.parseFloat(ofertaInputDto.getPuntosNecesarios()));
    oferta.setImagenIlustrativa(ofertaInputDto.getImagenIlustrativa());
    oferta.setDescripcion(ofertaInputDto.getDescripcion());
    oferta.setOfertante(colaborador);

    return oferta;
  }
}