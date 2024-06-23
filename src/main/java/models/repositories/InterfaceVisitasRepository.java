package models.repositories;

import models.entities.personas.tecnico.VisitaTecnica;

/**
 * Interfaz del repositorio que registra las visitas realizadas por los tecnicos.
 */

public interface InterfaceVisitasRepository {
  void guardar(VisitaTecnica visitaTecnica);
}
