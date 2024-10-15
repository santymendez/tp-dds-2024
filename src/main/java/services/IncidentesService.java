package services;

import dtos.IncidenteInputDto;
import java.time.LocalDateTime;
import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.IncidentesRepository;

/**
 * Service para instanciar incidentes a partir de sus DTOs.
 */

public class IncidentesService {

  private final GenericRepository genericRepository;
  private final IncidentesRepository incidentesRepository;

  public IncidentesService(
      GenericRepository genericRepository,
      IncidentesRepository incidentesRepository
  ) {
    this.genericRepository = genericRepository;
    this.incidentesRepository = incidentesRepository;
  }

  /**
   * Crea un incidente a partir de un DTO.
   *
   * @param incidenteDto DTO del incidente.
   * @param heladera Heladera a la que pertenece el incidente.
   * @param colaborador Colaborador que reporta el incidente.
   */

  public Incidente crear(IncidenteInputDto incidenteDto,
                         Heladera heladera, Colaborador colaborador) {
    Incidente incidente = Incidente.builder()
        .momentoIncidente(LocalDateTime.now())
        .tipo(TipoIncidente.FALLA_TECNICA)
        .descripcion(incidenteDto.getDescripcion())
        .imagen(incidenteDto.getImagen())
        .colaborador(colaborador)
        .heladera(heladera)
        .solucionado(false)
        .build();

    heladera.modificarEstado(TipoEstado.INACTIVA_FALLA_TECNICA);

    this.genericRepository.modificar(heladera);

    this.incidentesRepository.guardar(incidente);

    return incidente;
  }

  /**
   * Intenta habilitar una heladera si todos los incidentes fueron solucionados.
   *
   * @param heladera Heladera a habilitar.
   */

  public void intentarHabilitarHeladera(Heladera heladera) {
    if (this.incidentesSolucionados(heladera)) {
      heladera.modificarEstado(TipoEstado.ACTIVA);
      this.genericRepository.modificar(heladera);
    }
  }

  /**
   * Chequea si todos los incidentes de una heladera fueron solucionados.
   *
   * @param heladera Heladera a chequear.
   * @return true si todos los incidentes fueron solucionados, false en caso contrario.
   */

  public Boolean incidentesSolucionados(Heladera heladera) {
    List<Incidente> incidentes =
        this.incidentesRepository.buscarPorHeladera(heladera.getId());

    return incidentes.stream().allMatch(Incidente::getSolucionado);
  }

  /**
   * Chequea si un colaborador no fue alertado por un tipo de alerta.
   *
   * @param tipoAlerta Tipo de alerta a chequear.
   * @param heladera Heladera a chequear.
   * @return true si el colaborador no fue alertado por el tipo de alerta, false en caso contrario.
   */

  public Boolean noFueAlertadoPor(TipoEstado tipoAlerta, Heladera heladera) {
    List<Incidente> incidentes =
        this.incidentesRepository.buscarPorHeladera(heladera.getId());

    return incidentes.stream().noneMatch(incidente ->
        incidente.getTipoAlerta().equals(tipoAlerta) && !incidente.getSolucionado()
    );
  }
}
