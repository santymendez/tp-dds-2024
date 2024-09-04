package models.entities.direccion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa una dirección con una ubicación, longitud, latitud y provincia.
 */

@Getter
@Setter
@Entity
@Table(name = "direcciones")
public class Direccion extends Persistente {
  @Column(name = "ubicacion")
  private String ubicacion;

  @Column(name = "longitud")
  private Float longitud;

  @Column(name = "latitud")
  private Float latitud;

  @JoinColumn(name = "barrio_id", referencedColumnName = "id")
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Barrio barrio;
}
