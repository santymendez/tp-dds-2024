package modules.services.recomendator.entities;

/**
 * Clase molde para recibir el punto de la API Rest.
 */

public class Punto {
  int lon;
  int lat;
  String referencia;

  public int getLat() {
    return lat;
  }

  public int getLon() {
    return lon;
  }

  public String getReferencia() {
    return referencia;
  }
}
