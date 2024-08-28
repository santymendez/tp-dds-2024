package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Ciudad;
import models.entities.personas.tecnico.Tecnico;

/**
 * Interfaz Repositorio para los Técnicos.
 */

public interface InterfaceTecnicosRepository {
  public void guardar(Tecnico... tecnicos);

  public void guardar(Tecnico tecnico);

  public void modificar(Tecnico tecnico);

  public void eliminarFisico(Tecnico tecnico);

  public void eliminar(Tecnico tecnico);

  public Optional<Tecnico> buscarPorId(Long id);

  public List<Tecnico> buscarTodos();

  public List<Tecnico> buscarPorCiudad(Ciudad ciudad);
}
