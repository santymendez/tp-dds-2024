package services;

import dtos.VisitaInputDto;
import java.time.LocalDate;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.IncidentesRepository;

/**
 * Service para instanciar visitas técnicas a partir de sus DTOs.
 */

public class VisitasTecnicasService {
  private final GenericRepository genericRepository;
  private final IncidentesService incidentesService;

  /**
   * Constructor del service de visitas técnicas.
   *
   * @param genericRepository repositorio generico.
   * @param incidentesService service de incidentes.
   */

  public VisitasTecnicasService(
      GenericRepository genericRepository,
      IncidentesService incidentesService
  ) {
    this.genericRepository = genericRepository;
    this.incidentesService = incidentesService;
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
        .setIncidenteSolucionado(Boolean.parseBoolean(visitaInputDto.getIncidenteSolucionado()));
    visitaTecnica.setFechaVisita(LocalDate.parse(visitaInputDto.getFechaVisita()));
    visitaTecnica.setTrabajoRealizado(visitaInputDto.getTrabajoRealizado());

    visitaTecnica.setTecnico(tecnico);
    visitaTecnica.setIncidente(incidente);

    if (visitaTecnica.getIncidenteSolucionado()) {
      incidente.setSolucionado(true);
      this.genericRepository.modificar(incidente);
      this.incidentesService.intentarHabilitarHeladera(incidente.getHeladera());
    }

    this.genericRepository.guardar(visitaTecnica);
  }
}
