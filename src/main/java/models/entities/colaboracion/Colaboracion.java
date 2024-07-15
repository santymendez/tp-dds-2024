package models.entities.colaboracion;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
public class Colaboracion {
  private TipoColaboracion tipoColaboracion;
  private LocalDate fechaColaboracion;

  private DonacionDinero donacionDinero;

  private DonacionViandas donacionViandas;

  private DistribucionViandas distribucionViandas;

  private DistribucionTarjetas distribucionTarjetas;

  private ColocacionHeladera colocacionHeladera;

  private RealizacionOfertas realizarOfertas;


  public Colaboracion() {}
  //Se crea sin datos y se agregan los necesarios con setters

  //============================== Metodos Auxiliares ========================================

  public Integer tiempoActivaHeladera() {
    return this.colocacionHeladera.getHeladera().calcularMesesActiva();
  }

}
