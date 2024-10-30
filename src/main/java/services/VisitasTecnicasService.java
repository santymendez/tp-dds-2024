package services;

import dtos.VisitaInputDto;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.imp.GenericRepository;

/**
 * Service para instanciar visitas técnicas a partir de sus DTOs.
 */

public class VisitasTecnicasService {
  private final GenericRepository genericRepository;

  /**
   * Constructor del service de visitas técnicas.
   *
   * @param genericRepository repositorio generico.
   */

  public VisitasTecnicasService(
      GenericRepository genericRepository
  ) {
    this.genericRepository = genericRepository;
  }

  /**
   * Metodo que crea y guarda una visita técnica a partir del DTO.
   *
   * @param visitaInputDto DTO para crear y guardar la visita.
   */

  public void crear(VisitaInputDto visitaInputDto, Tecnico tecnico, Incidente incidente) {
    VisitaTecnica visitaTecnica = new VisitaTecnica();
    visitaTecnica.setFotoVisita(visitaInputDto.getFotoVisita());
    visitaTecnica
        .setIncidenteSolucionado(visitaInputDto.getIncidenteSolucionado());
    visitaTecnica.setFechaVisita(visitaInputDto.getFechaVisita());
    visitaTecnica.setTrabajoRealizado(visitaInputDto.getTrabajoRealizado());

    visitaTecnica.setTecnico(tecnico);
    visitaTecnica.setIncidente(incidente);

    if (visitaTecnica.getIncidenteSolucionado()) {
      incidente.setSolucionado(true);
      this.genericRepository.modificar(incidente);
    }

    this.genericRepository.guardar(visitaTecnica);
  }
}
