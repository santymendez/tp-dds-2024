package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa una ciudad con un nombre y un barrio.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ciudades")
public class Ciudad extends Persistente {

  @Column(name = "nombre", nullable = false)
  private String nombreCiudad;

  @JoinColumn(name = "provincia_id", nullable = false, referencedColumnName = "id")
  @ManyToOne
  private Provincia provincia;
}
