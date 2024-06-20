package models.repositories;

import java.util.Optional;
import models.entities.heladera.incidente.Incidente;

/**
 * Interfaz Repositorio para los Incidentes.
 */

public interface InterfaceIncidentesRepository {
  void guardar(Incidente incidente);
}
