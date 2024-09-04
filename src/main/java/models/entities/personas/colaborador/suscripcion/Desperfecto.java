package models.entities.personas.colaborador.suscripcion;

import java.util.List;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.searchers.BuscadorHeladerasFrecuentes;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;
import utils.sender.SenderLocator;

/**
 * Clase que representa la notificacion referida
 * a un desperfecto ocurrido en una heladera.
 */

@Setter
public class Desperfecto implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private SenderInterface senderInterface;
  private BuscadorHeladerasFrecuentes buscadorHeladerasFrecuentes;
  private List<Heladera> heladerasSugeridas;
  
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
  }

  /**
   * Enviar notificacion al colaborador.
   */

  public void intentarNotificar() {
    if (!this.heladerasSugeridas.isEmpty()) {
      this.notificar();
    }
  }

  /**
   * Envia una notificacion al colaborador.
   */

  public void notificar() {
    String destinatario = colaborador.getContacto().getInfo();

    String asunto = "La heladera " + this.heladera.getNombre() + " ha sufrido un desperfecto"
        + " y las viandas deben ser redistribuidas.";
    String cuerpo = "Estas son algunas de las heladeras a las que puedes llevar las viandas:\n"
        + this.nombresDeHeladerasString();

    Mensaje mensaje = new Mensaje(asunto, cuerpo);
    this.senderInterface.enviar(mensaje, destinatario);
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
