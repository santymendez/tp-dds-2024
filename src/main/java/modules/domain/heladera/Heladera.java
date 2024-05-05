package modules.domain.heladera;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Direccion;
import modules.domain.vianda.Vianda;

/**
 * Representa una heladera con una dirección, nombre, capacidad máxima de viandas, lista
 * de viandas y fecha de creación.
 */

@Getter
@Setter
public class Heladera {
  private Direccion direccion;
  private String nombre;
  private Integer capacidadMaximaViandas;
  private List<Vianda> viandas;
  private Date fechaDeCreacion;

  //TODO clases segunda entrega y metodos
  /*public void agregarVianda(Vianda vianda) {
    this.viandas.add(vianda);
  } */
}
