package utils.map;

import utils.recomendator.entities.Punto;
import java.io.IOException;

/**
 * Representa un mapa con la capacidad de crear marcadores.
 */

public class Map {

  public static void main(String[] args) throws IOException {
    Mark mark = new Mark();
    mark.agregar(new Punto("UTN FRBA Campus", "-34.659545675336126", "-58.46791554106547"));
  }
}