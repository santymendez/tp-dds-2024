package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.formulario.Formulario;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz repositorio para los formularios.
 */

public interface InterfaceFormulariosRepository extends PersistenciaSimple {

  void guardar(Formulario... formularios);

  void guardar(Formulario formulario);

  void modificar(Formulario formulario);

  void eliminarFisico(Formulario formulario);

  void eliminar(Formulario formulario);

  Optional<Formulario> buscarPorId(Long id);

  List<Formulario> buscarTodos();
}
