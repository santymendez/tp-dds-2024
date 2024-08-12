package models.repositories;

import models.entities.heladera.incidente.Incidente;

/**
 * Interfaz Repositorio para los Incidentes.
 */

public interface InterfaceIncidentesRepository {
  void guardar(Incidente incidente);
}
