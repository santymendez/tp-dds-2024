package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.personas.colaborador.canje.Canje;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz Repositorio para los Canjes.
 */

public interface InterfaceCanjesRepository extends PersistenciaSimple {

  void guardar(Canje... canjes);

  void guardar(Canje canje);

  void modificar(Canje canje);

  void eliminarFisico(Canje canje);

  void eliminar(Canje canje);

  Optional<Canje> buscarPorId(Long id);

  List<Canje> buscarTodos();
}
