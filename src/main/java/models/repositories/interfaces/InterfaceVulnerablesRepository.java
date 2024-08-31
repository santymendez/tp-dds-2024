package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Interfaz repositorio para los vulnerables.
 */

public interface InterfaceVulnerablesRepository {
  void guardar(Vulnerable ... vulnerables);

  void guardar(Vulnerable vulnerable);

  void modificar(Vulnerable vulnerable);

  void eliminarFisico(Vulnerable vulnerable);

  void eliminar(Vulnerable vulnerable);

  Optional<Vulnerable> buscarPorId(Long id);

  List<Vulnerable> buscarTodos();
}
