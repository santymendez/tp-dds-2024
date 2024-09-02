package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.Heladera;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz Repositorio para las Heladeras.
 */

public interface InterfaceHeladerasRepository extends PersistenciaSimple {

  void guardar(Heladera... heladeras);

  void guardar(Heladera heladera);

  void modificar(Heladera heladera);

  void eliminarFisico(Heladera heladera);

  void eliminar(Heladera heladera);

  Optional<Heladera> buscarPorId(Long id);

  List<Heladera> buscarTodos();
}
