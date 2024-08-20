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
 * Representa una ciudad con un nombre y un barrio.
 */

@Getter
@Setter
@Entity
@Table(name = "ciudades")
public class Ciudad extends EntidadPersistente {
  @Column(name = "nombre")
  private String nombreCiudad;

  @JoinColumn(name = "provincia_id")
  @ManyToOne
  private Provincia provincia;
}
