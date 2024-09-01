package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.formulario.Opcion;

/**
 * Interfaz del repositorio de opciones.
 */

public interface InterfaceOpcionesRepository extends PersistenciaSimple {

  void guardar(Opcion... opciones);

  void guardar(Opcion opcion);

  void modificar(Opcion opcion);

  void eliminarFisico(Opcion opcion);

  void eliminar(Opcion opcion);

  Optional<Opcion> buscarPorId(Long id);

  List<Opcion> buscarTodos();
}
