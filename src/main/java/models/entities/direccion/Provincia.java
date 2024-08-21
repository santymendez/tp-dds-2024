package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una provincia con un nombre y una ciudad.
 */

@Getter
@Setter
@Entity
@Table(name = "provincias")
public class Provincia {
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "nombre")
  private String nombreProvincia;
}
