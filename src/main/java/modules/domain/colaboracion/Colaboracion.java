package modules.domain.colaboracion;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;
import modules.domain.tarjeta.Tarjeta;
import modules.domain.vianda.Vianda;

/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
public class Colaboracion {
  private TipoColaboracion tipoColaboracion;

  //Donar dinero
  private Date fechaDonacion = null;
  private Integer monto = 0;
  private String frecuencia = null;

  //Donar vianda
  private List<Vianda> viandas = null;

  //Distribuir vianda
  private Heladera heladeraOrigen = null;
  private Heladera heladeraDestino = null;
  private Integer cantViandasDistribuidas = 0;
  private String motivoDistribucion = null;
  private Date fechaDistribucion = null;

  //Colocar heladera
  private Heladera heladera = null;

  //Distribuir tarjetas
  private HashSet<Tarjeta> tarjetasEntregadas;

  public Colaboracion() {}
  //Se crea sin datos y se agregan los necesarios con setters

  //============================== Metodos Auxiliares ========================================

  public Integer tiempoActivaHeladera() {
    return this.heladera.getMesesActiva();
  }

  public Integer cantViandasDonadas() {
    return this.viandas.size();
  }

  public Integer cantTarjetasEntregadas() {
    return this.tarjetasEntregadas.size();
  }
}
