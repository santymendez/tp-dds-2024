package modules.domain.colaboracion;


import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;
import modules.domain.vianda.Vianda;

/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
public class Colaboracion {
  private TipoColaboracion tipoColaboracion;

  //Donar dinero
  private Date fechaDonacion;
  private Integer monto;
  private String frecuencia;

  //Donar vianda
  private List<Vianda> viandas;

  //Distribuir vianda
  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private Integer cantViandasDistribuidas;
  private String motivoDistribucion;
  private Date fechaDistribucion;

  //Colocar heladera
  private Heladera heladera;

  //Distribuir tarjetas
  private Integer cantidadTarjetas;
}
