package models.entities.heladera.vianda;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa una vianda que incluye una comida, fecha de donación, heladera, calorías,
 * peso y estado de entrega.
 */

@Getter
@AllArgsConstructor
public class Vianda {
  private Comida comida;
  private LocalDate fechaDonacion;
  private Colaborador colaborador;
  private Heladera heladera;
  private Integer calorias;
  private Float peso;
  @Setter
  private Boolean entregada;
}
