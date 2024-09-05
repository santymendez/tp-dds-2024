package dtos;

import java.time.LocalDate;
import lombok.Data;
import models.entities.heladera.incidente.Incidente;
import models.entities.personas.tecnico.Tecnico;

/**
 * Representa el Input de una Visita Tecnica.
 */

@Data
public class VisitaInputDto {
  private Incidente incidente;
  private LocalDate fechaVisita;
  private String trabajoRealizado;
  private String fotoVisita;
  private Boolean incidenteSolucionado;
  private Tecnico tecnico;
}
