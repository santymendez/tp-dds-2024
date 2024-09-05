package models.entities.direccion;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;


/**
 * Representa un barrio con un nombre, calle y número.
 */
@Getter
@Setter
@Embeddable
public class Barrio {
  @Column(name = "nombreBarrio", nullable = false)
  private String nombreBarrio;

  @Column(name = "calle")
  private String calle;

  @Column(name = "numero")
  private Integer numero;

  @Embedded
  private Ciudad ciudad;
}
