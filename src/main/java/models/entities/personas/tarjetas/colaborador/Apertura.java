package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateTimeAttributeConverter;

/**
 * Clase que representa la apertura real de una heladera y lo que conlleva en el sistema.
 */

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class Apertura {

  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name = "fechaApertura")
  private LocalDateTime fechaApertura;

  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name = "fechaSolicitud")
  private LocalDateTime fechaSolicitud;

  public Apertura(LocalDateTime solicitud) {
    this.fechaSolicitud = solicitud;
  }

  @PrePersist
  protected void onInsert() {
    if (this.fechaSolicitud == null) {
      this.fechaSolicitud = LocalDateTime.now();
    }
  }
}
