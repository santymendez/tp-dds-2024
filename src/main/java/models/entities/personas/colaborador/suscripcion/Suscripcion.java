package models.entities.personas.colaborador.suscripcion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Clase abstracta que representa las suscripciones de
 * los colaboradores a heladeras.
 * Facilita la persistencia de las mismas.
 */

@Setter
@Entity
@Table(name = "suscripciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Suscripcion extends Persistente {

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  protected Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  protected Heladera heladera;

  @Transient
  protected SenderInterface senderInterface;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoSuscripcion")
  protected TipoSuscripcion tipo;

  public abstract Boolean seCumpleCondicion();

  public abstract String getAsunto();

  public abstract String getCuerpo();

  /**
   * Intenta notificar a los suscriptos en
   * caso de que se cumpla la condicion.
   */

  public void intentarNotificar() {
    if (this.seCumpleCondicion()) {
      this.notificar();
    }
  }

  /**
   * Envia una notificacion al colaborador.
   * Esta logica deberia ir en un controller.
   */

  public void notificar() {
    String destinatario = colaborador.getContacto().getInfo();
    String asunto = this.getAsunto();
    String cuerpo = this.getCuerpo();
    Mensaje mensaje = new Mensaje(asunto, cuerpo);
    this.senderInterface.enviar(mensaje, destinatario);
  }
}
