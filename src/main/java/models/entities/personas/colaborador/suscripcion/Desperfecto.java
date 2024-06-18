package models.entities.personas.colaborador.suscripcion;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import utils.sender.SenderInterface;

/**
 * Clase que representa la notificacion referida
 * a un desperfecto ocurrido en una heladera..
 */

public class Desperfecto implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private SenderInterface senderInterface;
  private List<Heladera> heladerasSugeridas;

  public void intentarNotificar() {
    this.notificar();
  }

  //TODO
  public void notificar() {

  }
}
