package models.entities.direccion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
@Embeddable
public class Ciudad {
  @Column(name = "nombreCiudad", nullable = false)
  private String nombreCiudad;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "provincia_id", referencedColumnName = "id")
  private Provincia provincia;
}
