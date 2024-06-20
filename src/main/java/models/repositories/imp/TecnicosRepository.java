package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.personas.InterfaceTecnicosRepository;

/**
 * Repositorio para los Tecnicos.
 */

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
   * @param ciudad Parametro de busqueda.
   * @return Una lista de Tecnicos si existen, sino una lista vacia.
   */

  public List<Tecnico> buscar(String ciudad) {
    return tecnicos
        .stream()
        .filter(tecnico ->
            tecnico.getAreaDeCobertura()
                .getNombreCiudad()
                .equals(ciudad)).toList();
  }
}
