package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.heladera.Heladera;
import models.repositories.interfaces.InterfaceHeladerasRepository;

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
  public List<Heladera> buscarTodas() {
    return this.getHeladeras();
  }

}
