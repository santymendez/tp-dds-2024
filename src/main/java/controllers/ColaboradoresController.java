package controllers;

import dtos.ColaboradorInputDto;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.ColaboradoresRepository;
import services.ColaboradoresService;

/**
 * Clase que representa al control del colaborador ;).
 */

public class ColaboradoresController {

  private final ColaboradoresRepository colaboradoresRepository;
  private final ColaboradoresService colaboradoresService;

  public ColaboradoresController(
      ColaboradoresRepository colaboradoresRepository,
      ColaboradoresService colaboradoresService) {
    this.colaboradoresRepository = colaboradoresRepository;
    this.colaboradoresService = colaboradoresService;
  }

  /**
   * Se busca si existe el colaborador, en caso contrario el service lo crea.
   *
   * @param colaboradorInputDto .
   */

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto) {
    Optional<Colaborador> unColaborador =
        colaboradoresRepository.buscar(colaboradorInputDto.getNumeroDocumento());

    return unColaborador.orElseGet(() -> colaboradoresService.crear(colaboradorInputDto));

  }
}
