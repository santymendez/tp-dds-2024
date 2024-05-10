package modules.domain.vianda;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;
import modules.domain.personas.colaborador.Colaborador;

/**
 * Representa una vianda que incluye una comida, fecha de donación, heladera, calorías,
 * peso y estado de entrega.
 */

@Getter
@AllArgsConstructor
public class Vianda {
  private Comida comida;
  private Date fechaDonacion;
  private Colaborador colaborador;
  private Heladera heladera;
  private Integer calorias;
  private Float peso;
  @Setter
  private Boolean entregada;
}
