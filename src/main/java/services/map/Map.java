package services.map;

import java.io.IOException;

/**
 * Representa un mapa con la capacidad de crear marcadores.
 */

public class Map {

  public void crearMarcador(String lugar, Double lat, Double lng) throws IOException {
    Mark mark = new Mark();
    mark.agregar(lugar, lat, lng);
  }

  public static void main(String[] args) throws IOException {
    Map map = new Map();
    map.crearMarcador("bs as", -34.597551, -58.445626);
  }
}