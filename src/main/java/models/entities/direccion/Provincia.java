package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;

/**
 * Representa una provincia con un nombre y una ciudad.
 */

@Getter
@Setter
@Entity
@Table(name = "provincias")
public class Provincia extends EntidadPersistente {

  @Column(name = "nombre")
  private String nombreProvincia;
}
