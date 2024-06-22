package models.entities.personas.colaborador.suscripcion;

import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.contacto.Contacto;
import utils.sender.Destinatario;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Clase que representa la notificación referida
 * a cuantas viandas faltan para que una heladera se llene.
 */

@Setter
public class FaltanViandas implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private Integer viandasFaltantes;
  private SenderInterface senderInterface;

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
    // TODO instanciar el sender
  }

  /**
   * Intenta notificar si es necesario.
   */

  public void intentarNotificar() {
    if (this.heladera.consultarEspacioSobrante().equals(this.viandasFaltantes)) {
      this.notificar();
    }
  }

  /**
   * Envia una notificacion al colaborador.
   */

  public void notificar() {
    Destinatario destinatario = new Destinatario();
    Contacto contacto = colaborador.getContacto();
    destinatario.agregarMedioDeContacto(contacto.getTipoContacto(),
        contacto.getContacto());
    String asunto = "Faltan " + viandasFaltantes + " para que una heladera se encuentre llena.";
    String cuerpo = "No deposites más viandas en la heladera: " + heladera.getNombre();
    Mensaje mensaje = new Mensaje(asunto, cuerpo);
    this.senderInterface.enviar(mensaje, destinatario);
  }
}
