package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Barrio;

/**
 * Interfaz repositorio barrios.
 */

public interface InterfaceBarriosRepository {
  void guardar(Barrio... barrios);

  void guardar(Barrio barrio);

  void modificar(Barrio barrio);

  void eliminarFisico(Barrio barrio);

  void eliminar(Barrio barrio);

  Optional<Barrio> buscarPorId(Long id);

  List<Barrio> buscarTodos();
}
