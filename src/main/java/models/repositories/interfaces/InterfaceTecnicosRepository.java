package models.repositories.interfaces;

import java.util.List;
import models.entities.personas.tecnico.Tecnico;

/**
 * Interfaz Repositorio para los Técnicos.
 */

public interface InterfaceTecnicosRepository {
  void guardar(Tecnico tecnico);

  List<Tecnico> buscarTodosPor(String ciudad);
}
