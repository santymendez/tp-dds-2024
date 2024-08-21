package dtos;

import java.awt.Image;
import java.time.LocalDate;
import lombok.Data;
import models.entities.heladera.incidente.Incidente;

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
}
