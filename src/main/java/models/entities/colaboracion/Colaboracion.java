package models.entities.colaboracion;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
public class Colaboracion {
  private TipoColaboracion tipoColaboracion;
  private LocalDate fechaColaboracion;

  //Donar dinero
  private Integer monto;
  private String frecuencia;

  //Donar vianda
  private List<Vianda> viandas;
  private Integer cantViandas;

  //Distribuir vianda
  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private Integer cantViandasDistribuidas;
  private String motivoDistribucion;

  //Colocar heladera
  private Heladera nuevaHeladera;

  //Distribuir tarjetas
  private List<TarjetaVulnerable> tarjetasEntregadas;
  private Integer cantTarjetasEntregadas;

  //Realizar ofertas
  private List<Oferta> ofertas;

  public Colaboracion() {}
  //Se crea sin datos y se agregan los necesarios con setters

  //============================== Metodos Auxiliares ========================================

  public Integer tiempoActivaHeladera() {
    return this.nuevaHeladera.calcularMesesActiva();
  }

  public void cantViandasDonadas() {
    this.cantViandas = this.viandas.size();
  }

  public void cantTarjetasEntregadas() {
    this. cantTarjetasEntregadas = tarjetasEntregadas.size();
  }


}
