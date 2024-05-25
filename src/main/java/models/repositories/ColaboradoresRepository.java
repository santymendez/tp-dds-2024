package models.repositories;

import java.util.HashSet;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.Colaborador;


/**
 * Clase de Colaborador para cargar los datos del CSV.
 */
@Getter
@Setter
public class ColaboradoresRepository {
  HashSet<Colaborador> colaboradores = new HashSet<>();

  public void guardar(Colaborador colaborador) {
    colaboradores.add(colaborador);
  }

  /**
   * Busca colaborador en la lista.
   *
   * @param nroDocumento key.
   */

  public Optional<Colaborador> buscar(Integer nroDocumento) {
    return colaboradores
        .stream()
        .filter(colaborador ->
            colaborador.getDocumento()
                .getNroDocumento()
                  .equals(nroDocumento))
        .findFirst();
  }
}
