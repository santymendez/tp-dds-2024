package models.repositories.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.personas.colaborador.Colaborador;

/**
 * Repositorio para los Colaboradores.
 */

@Getter
public class ColaboradoresRepository implements InterfaceColaboradoresRepository {
  private final List<Colaborador> colaboradores;

  public ColaboradoresRepository() {
    colaboradores = new ArrayList<>();
  }

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