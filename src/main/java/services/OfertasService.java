package services;

import dtos.OfertaDto;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.repositories.imp.ColaboradoresRepository;

/**
 * Service para las ofertas.
 */

public class OfertasService {

  private final ColaboradoresRepository colaboradoresRepository;

  public OfertasService(ColaboradoresRepository colaboradoresRepository) {
    this.colaboradoresRepository = colaboradoresRepository;
  }

  /**
   * Crea una oferta a partir de un DTO.
   *
   * @param ofertaDto el DTO con los datos del producto/servicio.
   * @return la oferta creada.
   */

  public Oferta crear(OfertaDto ofertaDto) {
    Optional<Colaborador> posibleOfertante = colaboradoresRepository
        .buscarPorId(Long.parseLong(ofertaDto.getOfertante()), Colaborador.class);

    //  if (posibleOfertante.isEmpty()) {
    //    throw new IllegalArgumentException("No existe ese colaborador");
    //  }

    Oferta oferta = new Oferta();
    oferta.setNombre(ofertaDto.getNombre());
    oferta.setPuntosNecesarios(Float.parseFloat(ofertaDto.getPuntosNecesarios()));
    oferta.setImagenIlustrativa(ofertaDto.getImagenIlustrativa());
    oferta.setDescripcion(ofertaDto.getDescripcion());

    //TODO
    Optional<Colaborador> colaborador = colaboradoresRepository.buscarPorDocumento(45345678);
    oferta.setOfertante(colaborador.get());

    return oferta;
  }
}