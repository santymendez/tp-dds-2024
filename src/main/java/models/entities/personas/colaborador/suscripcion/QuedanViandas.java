package models.entities.personas.colaborador.suscripcion;

import javax.persistence.Column;
import javax.persistence.Entity;
import config.SenderLocator;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa la notificacion referida
 * a cuantas viandas quedan en una heladera.
 */

@Setter
@NoArgsConstructor
@Entity
public class QuedanViandas extends Suscripcion {

  @Column(name = "viandasDisponibles")
  private Integer viandasDisponibles;

  /**
   * Instancia una suscripcion.
   *
   * @param colaborador Suscriptor.
   * @param heladera Heladera a la que el suscriptor busca suscribirse.
   * @param viandas Cantidad de viandas para ser notificado.
   */

  public QuedanViandas(Colaborador colaborador, Heladera heladera, Integer viandas) {
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.viandasDisponibles = viandas;
    this.senderInterface =
        SenderLocator.instanceOf(colaborador.getContacto().getTipoContacto());
    this.tipo = TipoSuscripcion.QUEDAN_N_VIANDAS;
  }

  @Override
  public Boolean seCumpleCondicion() {
    return this.heladera.consultarStock().equals(this.viandasDisponibles);
  }

  @Override
  public String getAsunto() {
    return "Quedan sólo " + viandasDisponibles + " en una heladera.";
  }

  @Override
  public String getCuerpo() {
    return "Acercate a la heladera: " + heladera.getNombre() + " para rellenarla";
  }
}
