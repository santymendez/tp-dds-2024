package models.entities.direccion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.EntidadPersistente;


/**
 * Representa un barrio con un nombre, calle y número.
 */
@Getter
@Setter
@Entity
@Table(name = "barrios")
public class Barrio extends EntidadPersistente {

  @Column(name = "nombre")
  private String nombreBarrio;

  @Column(name = "calle")
  private String calle;

  @Column(name = "numero")
  private Integer numero;

  @JoinColumn(name = "ciudad_id")
  @ManyToOne
  private Ciudad ciudad;
}
