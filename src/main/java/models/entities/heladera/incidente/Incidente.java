package models.entities.heladera.incidente;

import java.awt.Image;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa los incidentes en las heladeras.
 */

@Getter
@Setter
public class Incidente {
  private TipoIncidente tipo;
  private LocalDateTime momentoIncidente;
  private Heladera heladera;

  //ALERTA
  private TipoEstado tipoAlerta;

  //FALLA TECNICA
  private Colaborador colaborador;
  private String descripcion;
  private Image imagen;

  /**
   * Instancia la clase Incidente.
   *
   * @param tipo Tipo de incidente.
   * @param heladera Heladera afectada por el incidente.
   */

  public Incidente(TipoIncidente tipo, Heladera heladera) {
    this.tipo = tipo;
    this.heladera = heladera;
    this.momentoIncidente = LocalDateTime.now();
  }
}