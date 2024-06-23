package dtos;

import java.awt.Image;
import java.time.LocalDate;
import lombok.Data;
import models.entities.heladera.incidente.Incidente;

/**
 * Representa la clase DTO de las visitas técnicas.
 */

@Data
public class VisitaInputDto {
  private Incidente incidente;
  private LocalDate fechaVisita;
  private String trabajoRealizado;
  private Image fotoVisita;
  private Boolean incidenteSolucionado;
}
