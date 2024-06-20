package models.repositories.personas;

import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;

/**
 * Interfaz Repositorio para los Colaboradores.
 */

public interface InterfaceColaboradoresRepository {
  void guardar(Colaborador colaborador);

  Optional<Colaborador> buscar(Integer nroDocumento);
}
