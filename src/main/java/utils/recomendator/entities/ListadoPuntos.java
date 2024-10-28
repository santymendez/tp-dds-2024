package utils.recomendator.entities;

import java.util.List;
import lombok.Getter;

/**
 * Clase molde para recibir el listado de puntos de la API Rest.
 */

@Getter
public class ListadoPuntos {
  public List<Punto> puntos;
  public Integer cantidad;
  public Integer inicio;
  public Integer total;
}
