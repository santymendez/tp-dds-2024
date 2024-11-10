package models.repositories.imp;

import java.util.List;
import java.util.Optional;
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
   * @param nombreCiudad Parámetro de búsqueda.
   * @return Una lista de Técnicos si llegasen a existir, en otro caso una lista vacía.
   */

  public List<Tecnico> buscarPorCiudad(String nombreCiudad) {
    return entityManager()
        .createQuery(
            "FROM Tecnico tecnico WHERE tecnico.areaDeCobertura.nombreCiudad = :nombreCiudad",
            Tecnico.class)
        .setParameter("nombreCiudad", nombreCiudad)
        .getResultList();
  }

  /**
   * Busca un Tecnico por su id de usuario.
   *
   * @param idUsuario Id del usuario a buscar.
   * @return Un Optional con el Tecnico encontrado, o vacío si no se encontró.
   */

  public Optional<Tecnico> buscarPorIdUsuario(Long idUsuario) {
    String query = "SELECT t FROM Tecnico t WHERE t.usuario.id = :idUsuario";
    return entityManager().createQuery(query, Tecnico.class)
        .setParameter("idUsuario", idUsuario)
        .getResultList()
        .stream()
        .findFirst();
  }
}
