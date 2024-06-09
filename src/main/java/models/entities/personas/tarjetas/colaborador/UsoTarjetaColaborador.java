package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;

/**
 * Clase que representa un uso de la tarjeta de un colaborador.
 */

@Getter
@Setter
public class UsoTarjetaColaborador {
  private SolicitudApertura solicitud;
  private Apertura apertura;
  private Heladera heladera;

  /**
   * Instancia la clase de Uso.
   *
   * @param heladera Heladera solicitada para abrir.
   */

  public UsoTarjetaColaborador(Heladera heladera) {
    this.solicitud = new SolicitudApertura(LocalDateTime.now());
    this.apertura = new Apertura(this.solicitud);
    this.heladera = heladera;
  }
}
