package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.heladera.Heladera;

/**
 * Repositorio de heladeras para verificar la desconexión del sensor.
 */

@Getter
public class HeladerasRepository {
  private final List<Heladera> heladeras;
  private static HeladerasRepository instance;

  private HeladerasRepository() {
    this.heladeras = new ArrayList<>();
  }

  /**
   * Singleton para el Repositorio de Heladeras.
   *
   * @return Instancia del repositorio de Heladeras.
   */

  public static HeladerasRepository getInstance() {
    if (instance == null) {
      instance = new HeladerasRepository();
    }
    return instance;
  }

  public void guardar(Heladera heladera) {
    this.heladeras.add(heladera);
  }
}
