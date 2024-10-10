package services;

import dtos.IncidenteDto;
import java.time.LocalDateTime;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;

/**
 * Service para instanciar incidentes a partir de sus DTOs.
 */

public class IncidentesService {

  private final GenericRepository genericRepository;

  public IncidentesService(GenericRepository genericRepository) {
    this.genericRepository = genericRepository;
  }

  /**
   * Crea un incidente a partir de un DTO.
   *
   * @param incidenteDto DTO del incidente.
   * @param heladera Heladera a la que pertenece el incidente.
   * @param colaborador Colaborador que reporta el incidente.
   */

  public void crear(IncidenteDto incidenteDto, Heladera heladera, Colaborador colaborador) {
    Incidente incidente = Incidente.builder()
        .momentoIncidente(LocalDateTime.now())
        .tipo(TipoIncidente.FALLA_TECNICA)
        .descripcion(incidenteDto.getDescripcion())
        .imagen(incidenteDto.getImagen())
        .colaborador(colaborador)
        .heladera(heladera)
        .build();

    heladera.modificarEstado(TipoEstado.INACTIVA_FALLA_TECNICA);
    this.genericRepository.modificar(heladera);

    this.genericRepository.guardar(incidente);
  }
}
