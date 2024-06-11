package models.repositories;

import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;

/**
 * Repositorio que guarda los colaboradores.
 */

public interface InterfaceColaboradoresRepository {
  void guardar(Colaborador colaborador);

  Optional<Colaborador> buscar(Integer nroDocumento);



}
