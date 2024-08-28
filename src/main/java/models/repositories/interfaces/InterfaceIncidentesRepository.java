package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.incidente.Incidente;

/**
 * Interfaz Repositorio para los Incidentes.
 */

public interface InterfaceIncidentesRepository {
  void guardar(Incidente... incidentes);

  void guardar(Incidente incidente);

  void modificar(Incidente incidente);

  void eliminarFisico(Incidente incidente);

  void eliminar(Incidente incidente);

  Optional<Incidente> buscarPorId(Long id);

  List<Incidente> buscarTodos();
}
