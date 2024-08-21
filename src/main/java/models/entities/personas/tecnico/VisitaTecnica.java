package models.entities.personas.tecnico;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;

/**
 * Clase que representa la visita técnica que realiza un técnico a una heladera.
 */

@Getter
@Setter
public class VisitaTecnica {
  private Incidente incidente;
  private LocalDate fechaVisita;
  private String trabajoRealizado;
  private String fotoVisita = null;
  private Boolean incidenteSolucionado = false;

  /**
   * Metodo constructor de la visita tecnica.
   *
   * @param incidente incidente a revisar durante la visita tecnica.
   */

  public VisitaTecnica(Incidente incidente, String trabajoRealizado) {
    this.incidente = incidente;
    this.fechaVisita = LocalDate.now();
    this.trabajoRealizado = trabajoRealizado;
  }

  public void incidenteSolucionado() {
    this.incidenteSolucionado = true;
    this.incidente.getHeladera().getModEstados().modificarEstado(TipoEstado.ACTIVA);
  }
}
