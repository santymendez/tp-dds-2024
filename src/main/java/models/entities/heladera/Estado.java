package models.entities.heladera;


import java.time.LocalDate;
import java.time.Period;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el estado de una heladera, con el tipo de estado y la fecha registrada.
 */

@Getter
@Setter
public class Estado {
  private TipoEstado estado;
  private LocalDate fechaInicial;
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
