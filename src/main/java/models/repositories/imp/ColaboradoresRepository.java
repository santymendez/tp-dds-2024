package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.InterfaceColaboradoresRepository;

/**
 * Clase de Colaborador para cargar los datos del CSV.
 */
@Getter
@Setter
public class ColaboradoresRepository implements InterfaceColaboradoresRepository {
  List<Colaborador> colaboradores = new ArrayList<>();

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
