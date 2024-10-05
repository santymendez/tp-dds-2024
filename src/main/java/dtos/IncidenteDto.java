package dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para los incidentes.
 */

@Data
@NoArgsConstructor
public class IncidenteDto {
  private String tipoIncidente;
  private String momentoIncidente;
  private String heladera; // con el id encontrar la heladera
  private String tipoAlerta;
  private String colaborador; // con el id encontrar la heladera
  private String descripcion;
  private String imagen;
}
