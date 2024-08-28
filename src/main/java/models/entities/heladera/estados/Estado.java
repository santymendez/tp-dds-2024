package models.entities.heladera.estados;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;

/**
 * Clase que representa el estado de una heladera, con el tipo de estado y la fecha registrada.
 */

@Getter
@Setter
@Entity
@Table(name = "estados")
@NoArgsConstructor
public class Estado extends Persistente {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoEstado estado;

  @Column(name = "fechaInicial", columnDefinition = "DATE")
  private LocalDate fechaInicial;

  @Column(name = "fechaFinal", columnDefinition = "DATE")
  private LocalDate fechaFinal;

  public Estado(TipoEstado estadoHeladera) {
    this.estado = estadoHeladera;
  }

  /**
   * Calcula los meses de diferencia entre la fechaInicial y la fechaFinal.
   */

  public Integer calcularMeses() {
    if (this.fechaFinal == null) {
      this.fechaFinal = LocalDate.now();
    }
    Period period = Period.between(this.fechaInicial, this.fechaFinal);
    return period.getYears() * 12 + period.getMonths();
  }
}
