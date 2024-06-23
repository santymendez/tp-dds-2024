package models.entities.personas.tecnico;

import java.awt.Image;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.TipoEstado;
import models.entities.heladera.incidente.Incidente;

/**
 * Clase que representa la visita técnica que realiza un técnico a una heladera.
 */

@Getter
@Setter //Segun vaya realizando la visita, con los setters el tecnico puede
//agregar imagen y marcar
public class VisitaTecnica {
  private Incidente incidente;
  private LocalDate fechaVisita;
  private String trabajoRealizado;
  private Image fotoVisita = null;
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
    this.incidente.getHeladera().modificarEstado(TipoEstado.ACTIVA);
  }
}
