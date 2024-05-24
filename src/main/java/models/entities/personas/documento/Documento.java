package models.entities.personas.documento;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa un documento, con el número y el tipo.
 */

@Getter
@AllArgsConstructor
public class Documento {
  private final Integer nroDocumento;
  private final TipoDocumento tipoDocumento;
}
