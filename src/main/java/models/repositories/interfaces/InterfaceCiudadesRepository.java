package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Ciudad;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz Repositorio para las Ciudades.
 */

public interface InterfaceCiudadesRepository extends PersistenciaSimple {

  void guardar(Ciudad... ciudades);

  void guardar(Ciudad ciudad);

  void modificar(Ciudad ciudad);

  void eliminarFisico(Ciudad ciudad);

  void eliminar(Ciudad ciudad);

  Optional<Ciudad> buscarPorId(Long id);

  List<Ciudad> buscarTodos();
}
