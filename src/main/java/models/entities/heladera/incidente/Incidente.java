package models.entities.heladera.incidente;

import java.awt.Image;
import java.time.LocalDateTime;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa los incidentes en las heladeras.
 */

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
}
