package models.entities.heladera;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un modelo de Heladera. Tiene como atributos su modelo y un sensor de temperatura.
 */

@Getter
@Setter
@Embeddable
public class Modelo {

  @Column(name = "modelo")
  private String nombre;

  @Column(name = "temperaturaMinima", nullable = false)
  private Float temperaturaMinima;

  @Column(name = "temperaturaMaxima", nullable = false)
  private Float temperaturaMaxima;
}
