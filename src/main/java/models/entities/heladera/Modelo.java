package models.entities.heladera;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa un modelo de Heladera.
 */

@Getter
@Setter
@Entity
@Table(name = "modelos")
public class Modelo extends Persistente {
  @Column(name = "modelo")
  private String nombre;

  @Column(name = "temperaturaMinima", nullable = false)
  private Float temperaturaMinima;

  @Column(name = "temperaturaMaxima", nullable = false)
  private Float temperaturaMaxima;
}
