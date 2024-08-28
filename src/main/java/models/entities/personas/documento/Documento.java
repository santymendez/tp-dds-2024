package models.entities.personas.documento;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Representa un documento, con el número y el tipo.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Documento {
  @Column(name = "nroDocumento")
  private Integer nroDocumento;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoDocumento")
  private TipoDocumento tipoDocumento;
}
