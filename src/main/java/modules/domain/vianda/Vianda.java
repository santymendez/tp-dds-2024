package modules.domain.vianda;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;

/**
 * Representa una vianda que incluye una comida, fecha de donación, heladera, calorías,
 * peso y estado de entrega.
 */

@Getter
@Setter
public class Vianda {
  private Comida comida;
  private Date fechaDonacion;
  //private PersonaFisica colaborador;
  private Heladera heladera;
  private Integer calorias;
  private Float peso;
  private Boolean entregada;
}
