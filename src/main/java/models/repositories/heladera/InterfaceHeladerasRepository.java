package models.repositories.heladera;

import java.util.List;
import models.entities.heladera.Heladera;

/**
 * Interfaz Repositorio para las Heladeras.
 */

public interface InterfaceHeladerasRepository {
  void guardar(Heladera heladera);

  List<Heladera> obtenerHeladeras();
}
