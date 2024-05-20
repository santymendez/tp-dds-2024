package modules.services.recomendator.entities;

import java.util.List;

/**
 * Clase molde para recibir el listado de puntos de la API Rest.
 */

public class ListadoDepuntos {
  public int latitud;
  public int longitud;
  public int radio;
  public String direccion;
  public List<Punto> puntos;
  public int cantidad;
  public int inicio;
  public int total;
}
