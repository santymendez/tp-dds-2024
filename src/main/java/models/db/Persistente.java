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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(name = "activo", nullable = false)
  private Boolean activo;

  @Column(name = "fechaAlta", columnDefinition = "DATE", nullable = false)
  private LocalDate fechaAlta;

  public Persistente() {
    this.fechaAlta = LocalDate.now();
    this.activo = true;
  }

  public boolean recienCargado() {
    return fechaAlta.isAfter(LocalDate.now().minusMonths(1));
  }
}
