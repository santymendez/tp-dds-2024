package models.entities.personas.tecnico;

import java.awt.Image;
import java.time.LocalDate;
import models.entities.heladera.incidente.Incidente;

/**
 * Clase que representa la visita técnica que realiza un técnico a una heladera.
 */

public class VisitaTecnica {
  private Incidente incidente;
  private LocalDate fechaVisita;
  private String trabajoRealizado;
  private Image fotoVisita;
  private Boolean incidenteSolucionado;
}
