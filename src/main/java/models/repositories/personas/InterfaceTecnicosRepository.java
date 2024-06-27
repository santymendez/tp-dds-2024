package models.repositories.personas;

import java.util.List;
import models.entities.personas.tecnico.Tecnico;

/**
 * Interfaz Repositorio para los Técnicos.
 */

public interface InterfaceTecnicosRepository {
  void guardar(Tecnico tecnico);

  List<Tecnico> buscar(String ciudad);
}
