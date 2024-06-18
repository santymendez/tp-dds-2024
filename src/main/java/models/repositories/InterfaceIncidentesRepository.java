package models.repositories;

import java.util.Optional;
import models.entities.heladera.incidente.Incidente;

/**
 * Representa una interfaz para un repositorio de Incidentes.
 */

public interface InterfaceIncidentesRepository {
  void guardar(Incidente incidente);
}
