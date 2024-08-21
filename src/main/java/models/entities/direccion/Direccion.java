package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una dirección con una ubicación, longitud, latitud y provincia.
 */

@Getter
@Setter
@Entity
@Table(name = "direcciones")
public class Direccion {
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "ubicacion")
  private String ubicacion;

  @Column(name = "longitud")
  private Float longitud;

  @Column(name = "latitud")
  private Float latitud;

  @JoinColumn(name = "barrio_id")
  @ManyToOne
  private Barrio barrio;
}
