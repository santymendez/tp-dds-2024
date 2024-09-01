package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Ciudad;
import models.entities.personas.tecnico.Tecnico;

/**
 * Interfaz Repositorio para los Técnicos.
 */

public interface InterfaceTecnicosRepository extends PersistenciaSimple {

  void guardar(Tecnico... tecnicos);

  void guardar(Tecnico tecnico);

  void modificar(Tecnico tecnico);

  void eliminarFisico(Tecnico tecnico);

  void eliminar(Tecnico tecnico);

  Optional<Tecnico> buscarPorId(Long id);

  List<Tecnico> buscarTodos();

  List<Tecnico> buscarPorCiudad(Ciudad ciudad);
}
