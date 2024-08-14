package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.interfaces.InterfaceTecnicosRepository;

/**
 * Repositorio para los Tecnicos.
 */

@Getter
public class TecnicosRepository implements InterfaceTecnicosRepository {
  private final List<Tecnico> tecnicos;

  public TecnicosRepository() {
    this.tecnicos = new ArrayList<>();
  }

  public void guardar(Tecnico tecnico) {
    this.tecnicos.add(tecnico);
  }

  /**
   * Busca Tecnicos en el Repositorio.
   *
   * @param ciudad Parámetro de búsqueda.
   * @return Una lista de Técnicos si llegasen a existir, en otro caso una lista vacía.
   */

  public List<Tecnico> buscarTodosPor(String ciudad) {
    return tecnicos
        .stream()
        .filter(tecnico ->
            tecnico.getAreaDeCobertura()
                .getNombreCiudad()
                .equals(ciudad)).toList();
  }
}
