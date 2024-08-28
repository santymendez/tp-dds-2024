package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;

/**
 * Interfaz Repositorio para las Colaboraciones.
 */

public interface InterfaceColaboracionesRepository {
  void guardar(Colaboracion colaboracion);

  void modificar(Colaboracion colaboracion);

  void eliminarFisico(Colaboracion colaboracion);

  void eliminar(Colaboracion colaboracion);

  Optional<Colaboracion> buscarPorId(Long id);

  List<Colaboracion> buscarPorTipo(TipoColaboracion tipoColaboracion);

  List<Colaboracion> buscarTodos();
}
