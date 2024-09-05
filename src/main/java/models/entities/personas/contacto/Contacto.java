package models.entities.personas.contacto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Representa un contacto, puede ser un mail, un telefono fijo o un numero de celular.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Contacto {

  @Column(name = "info", nullable = false)
  private String info;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_contacto", nullable = false)
  private TipoContacto tipoContacto;
}
