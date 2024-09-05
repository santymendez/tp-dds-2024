package models.entities.personas.colaborador.suscripcion;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.searchers.BuscadorHeladerasFrecuentes;
import utils.sender.SenderLocator;

/**
 * Clase que representa la notificacion referida
 * a un desperfecto ocurrido en una heladera.
 */

@Setter
@NoArgsConstructor
@Entity
public class Desperfecto extends Suscripcion {

  @Transient
  private BuscadorHeladerasFrecuentes buscadorHeladerasFrecuentes;
  
  /**
   * Constructor de la suscripcion en caso de que ocurran fallas.
   *
   * @param colaborador Suscriptor.
   * @param heladera Heladera a la que el suscriptor busca suscribirse.
   */

  public Desperfecto(Colaborador colaborador, Heladera heladera) {
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.buscadorHeladerasFrecuentes = new BuscadorHeladerasFrecuentes();
    this.senderInterface =
        SenderLocator.getService(colaborador.getContacto().getTipoContacto());
    this.tipo = TipoSuscripcion.OCURRIO_DESPERFECTO;
  }

  @Override
  public Boolean seCumpleCondicion() {
    return true;
  }

  @Override
  public String getAsunto() {
    return "La heladera " + this.heladera.getNombre() + " ha sufrido un desperfecto"
        + " y las viandas deben ser redistribuidas.";
  }

  @Override
  public String getCuerpo() {
    return "Estas son algunas de las heladeras a las que puedes llevar las viandas:\n"
        + this.nombresDeHeladerasString();
  }

  /**
   * Genera un string con los nombres de las heladeras frecuentes.
   *
   * @return El string generado.
   */

  public String nombresDeHeladerasString() {
    List<Heladera> heladerasFrecuentes =
        this.buscadorHeladerasFrecuentes.heladerasFrecuentes(this.colaborador);
    StringBuilder s = new StringBuilder();
    for (Heladera heladera : heladerasFrecuentes) {
      s.append(heladera.getNombre()).append("\n");
    }
    return s.toString();
  }
}
