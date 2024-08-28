package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa una provincia con un nombre y una ciudad.
 */

@Getter
@Setter
@Entity
@Table(name = "provincias")
public class Provincia extends Persistente {

  @Column(name = "nombre", nullable = false)
  private String nombreProvincia;
}
