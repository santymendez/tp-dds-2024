package models.entities.personas.colaborador.suscripcion;

import config.SenderLocator;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa la notificación referida
 * a cuantas viandas faltan para que una heladera se llene.
 */

@Setter
@NoArgsConstructor
@Entity
public class FaltanViandas extends Suscripcion {

  @Column(name = "viandasFaltantes")
  @Setter
  private Integer viandasFaltantes;

  /**
   * Instancia una suscripcion.
   *
   * @param colaborador Suscriptor.
   * @param heladera Heladera a la que el suscriptor busca suscribirse.
   * @param viandas Cantidad de viandas para ser notificado.
   */

  public FaltanViandas(Colaborador colaborador, Heladera heladera, Integer viandas) {
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.viandasFaltantes = viandas;
  }

  @Override
  public Boolean seCumpleCondicion() {
    return this.heladera
        .consultarEspacioSobrante()
        .equals(this.viandasFaltantes);
  }

  @Override
  public String getAsunto() {
    return "Faltan " + this.viandasFaltantes + " para que una heladera se encuentre llena.";
  }

  @Override
  public String getCuerpo() {
    return "No deposites más viandas en la heladera: " + this.heladera.getNombre();
  }
}
