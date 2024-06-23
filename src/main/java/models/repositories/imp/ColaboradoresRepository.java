package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.personas.InterfaceColaboradoresRepository;

/**
 * Repositorio para los Colaboradores.
 */

@Getter
public class ColaboradoresRepository implements InterfaceColaboradoresRepository {
  private final List<Colaborador> colaboradores;
  private static ColaboradoresRepository instance;

  private ColaboradoresRepository() {
    colaboradores = new ArrayList<>();
  }

  /**
   * Singleton para el repositorio de Colaboradores.
   *
   * @return Instancia del repositorio de Colaboradores.
   */

  public static ColaboradoresRepository getInstance() {
    if (instance == null) {
      instance = new ColaboradoresRepository();
    }
    return instance;
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
