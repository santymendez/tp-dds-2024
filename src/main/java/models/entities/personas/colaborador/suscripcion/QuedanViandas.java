package models.entities.personas.colaborador.suscripcion;

import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import utils.sender.SenderInterface;

/**
 * Clase que representa la notificacion referida
 * a cuantas viandas quedan en una heladera.
 */

public class QuedanViandas implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private SenderInterface senderInterface;
  private Integer viandasDisponibles;

  /**
   * Intenta notificar si es necesario.
   */

  public void intentarNotificar() {
    if (this.heladera.consultarStock().equals(this.viandasDisponibles)) {
      this.notificar();
    }
  }

  //TODO
  public void notificar() {}
}
