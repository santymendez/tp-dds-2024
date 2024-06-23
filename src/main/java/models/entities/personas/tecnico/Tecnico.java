package models.entities.personas.tecnico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.entities.direccion.Ciudad;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;

/**
 * Representa a un técnico en el sistema.
 * Un técnico tiene un nombre, apellido, tipo y número de documento, CUIL, medio de contacto
 * y área de cobertura.
 */

@Getter
@Setter // Modificación de técnicos.
@AllArgsConstructor // Dar de alta técnicos.
public class Tecnico {
  private String nombre;
  private String apellido;
  private Documento documento;
  private Integer cuil;
  private Contacto contacto;
  private Ciudad areaDeCobertura;

  /**
   * Método que permite al técnico registrar una visita a una heladera.
   *
   * @param heladera Heladera visitada para arreglar.
   */

  public Boolean puedeVisitar(Heladera heladera) {
    return !heladera.getEstadoActual().getEstado().equals(TipoEstado.ACTIVA);
  }

}
