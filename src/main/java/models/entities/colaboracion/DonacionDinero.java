package models.entities.colaboracion;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Colaboracion para Donar Dinero.
 */

@Getter
@Setter
@Embeddable
public class DonacionDinero {
  @Column(name = "dineroDonado")
  private Integer montoDonado;

  @Column(name = "frecuenciaDonacionDinero")
  private String frecuenciaDonacion;
}
