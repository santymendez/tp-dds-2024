package controllers;

import dtos.VisitaInputDto;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.VisitasRepository;

/**
 * Representa al controlador del repositorio de visitas.
 */

public class VisitasController {
  private final VisitasRepository visitasRepository;

  public VisitasController(VisitasRepository visitasRepository) {
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
        visitaInputDto.getTrabajoRealizado()
    );
    visitaTecnica.setIncidenteSolucionado(visitaInputDto.getIncidenteSolucionado());
    if (visitaInputDto.getFotoVisita() != null) {
      visitaTecnica.setFotoVisita(visitaInputDto.getFotoVisita());
    }

    visitasRepository.guardar(visitaTecnica);

    return visitaTecnica;
  }
}