package models.repositories;

import models.entities.colaboracion.Colaboracion;

/**
 * Interfaz Repositorio para las Colabroaciones.
 */

public interface InterfaceColaboracionesRepository {
  void guardar(Colaboracion colaboracion);
}
