package services;

import dtos.IncidenteDto;
import java.time.LocalDateTime;
import java.util.Optional;
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

  private final GenericRepository repo;

  public IncidentesService(GenericRepository repo) {
    this.repo = repo;
  }

  /**
   * Crea un incidente a partir de un DTO.
   *
   * @param input DTO con los datos del incidente.
   * @return Incidente instanciado.
   */

  public Incidente crear(IncidenteDto input) {
    Incidente incidente = new Incidente();
    incidente.setTipo(TipoIncidente.valueOf(input.getTipoIncidente()));
    incidente.setMomentoIncidente(LocalDateTime.parse(input.getMomentoIncidente()));

    if (input.getHeladera() != null) {
      Optional<Heladera> posibleHeladera = repo.buscarPorId(Long.parseLong(input.getHeladera()),
          Heladera.class);

      incidente.setHeladera(posibleHeladera.orElse(null));
    }

    incidente.setTipoAlerta(TipoEstado.valueOf(input.getTipoAlerta()));

    if (input.getColaborador() != null) {
      Optional<Colaborador> posibleColaborador = repo.buscarPorId(
          Long.parseLong(input.getColaborador()), Colaborador.class);

      incidente.setColaborador(posibleColaborador.orElse(null));
    }

    incidente.setDescripcion(input.getDescripcion());
    incidente.setImagen(input.getImagen());

    return incidente;
  }
}
