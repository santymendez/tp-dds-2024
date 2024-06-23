package controllers;

import dtos.VisitaInputDto;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.imp.VisitasRepository;

/**
 * Representa al controlador del repositorio de visitas.
 */

public class VisitasController {
  private final VisitasRepository visitasRepository;
  private static VisitasController instance;

  private VisitasController(VisitasRepository visitasRepository) {
    this.visitasRepository = visitasRepository;
  }

  /**
   * Singleton para el Controller de las Visitas Técnicas.
   *
   * @return Instancia del Controller de las Visitas Técnicas.
   */

  public static VisitasController getInstance() {
    if (instance == null) {
      instance = new VisitasController(VisitasRepository.getInstance());
    }
    return instance;
  }

  /**
   * Método que crea y guarda una visita técnica a partir del DTO.
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