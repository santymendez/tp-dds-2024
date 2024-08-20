package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase que representa la apertura real de una heladera y lo que conlleva en el sistema.
 */

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class Apertura {

  @Column(columnDefinition = "DATE") // TODO creo que el conversor no sirve para eso, necesitamos otro
  private LocalDateTime fechaApertura;
  @Column(columnDefinition = "DATE")
  private LocalDateTime fechaSolicitud;

  public Apertura(LocalDateTime solicitud) {
    this.fechaSolicitud = solicitud;
  }
}
