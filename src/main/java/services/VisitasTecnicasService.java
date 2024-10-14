package services;

import dtos.VisitaInputDto;
import models.entities.heladera.Heladera;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.imp.GenericRepository;
import java.time.LocalDate;

public class VisitasTecnicasService {
  private final GenericRepository genericRepository;

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
    visitaTecnica.setIncidenteSolucionado(Boolean.parseBoolean(visitaInputDto.getIncidenteSolucionado()));
    visitaTecnica.setFechaVisita(LocalDate.parse(visitaInputDto.getFechaVisita()));
    visitaTecnica.setTrabajoRealizado(visitaInputDto.getTrabajoRealizado());

    visitaTecnica.setTecnico(tecnico);
    visitaTecnica.setIncidente(incidente);

    //TODO CHEQUEAR PARA ACTIVAR LA HELADERA

    this.genericRepository.guardar(visitaTecnica);
  }
}
