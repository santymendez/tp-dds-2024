package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Ciudad;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tecnico.Tecnico;

/**
 * Repositorio para los Tecnicos.
 */

public class TecnicosRepository extends GenericRepository {

  public void guardar(Tecnico tecnico) {
    super.guardar(tecnico);
  }

  public void modificar(Tecnico tecnico) {
    super.modificar(tecnico);
  }

  public void eliminarFisico(Tecnico tecnico) {
    super.eliminarFisico(tecnico);
  }

  public void eliminar(Tecnico tecnico) {
    super.eliminar(tecnico);
  }

  public Optional<Tecnico> buscarPorId(Long id) {
    return super.buscarPorId(id, Tecnico.class);
  }

  public List<Tecnico> buscarTodos() {
    return super.buscarTodos(Tecnico.class);
  }

  /**
   * Busca Tecnicos en el Repositorio.
   *
   * @param ciudad Parámetro de búsqueda.
   * @return Una lista de Técnicos si llegasen a existir, en otro caso una lista vacía.
   */

  public List<Tecnico> buscarPorCiudad(Ciudad ciudad) {
    return entityManager()
        .createQuery("FROM Tecnico tecnico WHERE tecnico.areaDeCobertura = :ciudad", Tecnico.class)
        .setParameter("ciudad", ciudad)
        .getResultList();
  }

  public Optional<Tecnico> buscarPorIdUsuario(Long idUsuario) {
    String query = "SELECT t FROM Tecnico t WHERE t.usuario.id = :idUsuario";
    return entityManager().createQuery(query, Tecnico.class)
        .setParameter("idUsuario", idUsuario)
        .getResultList()
        .stream()
        .findFirst();
  }
}
