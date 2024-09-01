package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Direccion;

/**
 * Interfaz repositorio direcciones.
 */

public interface InterfaceDireccionesRepository extends PersistenciaSimple {

  void guardar(Direccion... direcciones);

  void guardar(Direccion direccion);

  void modificar(Direccion direccion);

  void eliminarFisico(Direccion direccion);

  void eliminar(Direccion direccion);

  Optional<Direccion> buscarPorId(Long id);

  List<Direccion> buscarTodos();
}
