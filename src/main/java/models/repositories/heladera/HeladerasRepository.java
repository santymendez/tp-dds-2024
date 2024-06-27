package models.repositories.heladera;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.heladera.Heladera;

/**
 * Repositorio para las Heladeras.
 */

@Getter
public class HeladerasRepository implements InterfaceHeladerasRepository {
  private final List<Heladera> heladeras;

  public HeladerasRepository() {
    this.heladeras = new ArrayList<>();
  }

  public void guardar(Heladera heladera) {
    this.heladeras.add(heladera);
  }

  @Override
  public List<Heladera> obtenerHeladeras() {
    return this.getHeladeras();
  }

}
