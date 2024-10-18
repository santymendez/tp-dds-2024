package models.entities.personas.colaborador.suscripcion;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa la notificación referida
 * a cuantas viandas faltan para que una heladera se llene.
 */

@Setter
@Entity
public class FaltanViandas extends Suscripcion {

  @Column(name = "viandasFaltantes")
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
    this.tipo = TipoSuscripcion.FALTAN_N_VIANDAS;
  }

  public FaltanViandas() {
    this.tipo = TipoSuscripcion.FALTAN_N_VIANDAS;
  }

  @Override
  public Boolean seCumpleCondicion() {
    return this.heladera
        .consultarEspacioSobrante()
        .equals(this.viandasFaltantes);
  }

  @Override
  public String getAsunto() {
    return "Faltan " + this.viandasFaltantes + " viandas para que la heladera se encuentre llena.";
  }

  @Override
  public String getCuerpo() {
    return "No deposites más o distribuye las viandas en la heladera: " + this.heladera.getNombre();
  }
}
