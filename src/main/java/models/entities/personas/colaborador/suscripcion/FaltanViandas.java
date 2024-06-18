package models.entities.personas.colaborador.suscripcion;

import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import utils.sender.SenderInterface;

/**
 * Clase que representa la notificacion referida
 * a cuantas viandas faltan para que una heladera se llene.
 */

public class FaltanViandas implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private SenderInterface senderInterface;
  private Integer viandasFaltantes;

  @Override
  public void intentarNotificar() {
    if (this.heladera.consultarEspacioSobrante().equals(this.viandasFaltantes)) {
      this.notificar();
    }
  }

  //TODO
  @Override
  public void notificar() {}
}
