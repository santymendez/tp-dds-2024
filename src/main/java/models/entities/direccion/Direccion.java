package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
  @Column(name = "nombreUbicacion", nullable = false)
  private String nombreUbicacion;

  @Column(name = "longitud")
  private Float longitud;

  @Column(name = "latitud")
  private Float latitud;

  @Embedded
  private Barrio barrio;

  //Si cualquiera de los datos de la direccion es null, no se puede hacer el envio
  public Boolean admiteEnvio() {
    return this.getBarrio().getCiudad().getProvincia() != null &&
        this.getBarrio().getCiudad().getNombreCiudad() != null &&
        this.getBarrio().getNombreBarrio() != null &&
        this.getBarrio().getCalle() != null &&
        this.getBarrio().getNumero() != null;
  }
}
