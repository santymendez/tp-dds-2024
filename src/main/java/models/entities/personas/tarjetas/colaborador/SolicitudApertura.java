package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa una solicitud de apertura de una heladera.
 */

@Getter
@Setter
@AllArgsConstructor
public class SolicitudApertura {
  private LocalDateTime fechaSolicitud;
}
