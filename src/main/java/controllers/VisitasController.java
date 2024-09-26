package controllers;

import config.RepositoryLocator;
import dtos.VisitaInputDto;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.imp.GenericRepository;

/**
 * Representa al controlador del repositorio de visitas.
 */

public class VisitasController {

  private final GenericRepository visitasRepository;

  public VisitasController(GenericRepository visitasRepository) {
    this.visitasRepository = visitasRepository;
  }

  /**
   * Metodo que crea y guarda una visita técnica a partir del DTO.
   *
   * @param visitaInputDto DTO para crear y guardar la visita.
   */

  public VisitaTecnica crearCon(VisitaInputDto visitaInputDto) {
    VisitaTecnica visitaTecnica = new VisitaTecnica(
        visitaInputDto.getIncidente(),
        visitaInputDto.getTrabajoRealizado(),
        visitaInputDto.getTecnico()
    );
    visitaTecnica.setIncidenteSolucionado(visitaInputDto.getIncidenteSolucionado());
    if (visitaInputDto.getFotoVisita() != null) {
      visitaTecnica.setFotoVisita(visitaInputDto.getFotoVisita());
    }

    this.visitasRepository.guardar(visitaTecnica);

    return visitaTecnica;
  }
}