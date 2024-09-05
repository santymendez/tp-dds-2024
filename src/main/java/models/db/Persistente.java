package models.db;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta que representa a las entidades persistentes.
 */

@MappedSuperclass
@Getter
@Setter
public abstract class Persistente {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "activo")
  private Boolean activo;

  @Column(name = "fechaAlta", columnDefinition = "DATE")
  private LocalDate fechaAlta;

  public Persistente() {
    this.fechaAlta = LocalDate.now();
    this.activo = true;
  }
}
