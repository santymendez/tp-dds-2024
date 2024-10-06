package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;

/**
 * Repositorio para los Colaboradores.
 */

public class ColaboradoresRepository extends GenericRepository {

  public void guardar(Colaborador colaborador) {
    super.guardar((colaborador));
  }

  public void modificar(Colaborador colaborador) {
    super.modificar(colaborador);
  }

  public void eliminarFisico(Colaborador colaborador) {
    super.eliminarFisico(colaborador);
  }

  public void eliminar(Colaborador colaborador) {
    super.eliminar(colaborador);
  }

  /**
   * Busca un colaborador por su id.
   *
   * @param id Id del colaborador a buscar.
   *
   * @return Un Optional con el colaborador encontrado, o vacío si no se encontró.
   */

  public Optional<Colaborador> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, Colaborador.class);
  }

  public List<Colaborador> buscarTodos() {
    return super.buscarTodos(Colaborador.class);
  }

  /**
   * Busca un colaborador por su número de documento.
   *
   * @param numeroDocumento Número de documento del colaborador a buscar.
   *
   * @return Un Optional con el colaborador encontrado, o vacío si no se encontró.
   */

  public Optional<Colaborador> buscarPorDocumento(Integer numeroDocumento) {
    String query = "SELECT c FROM Colaborador c WHERE c.documento.nroDocumento =: numeroDocumento";
    List<Colaborador> results = entityManager().createQuery(query, Colaborador.class)
            .setParameter("numeroDocumento", numeroDocumento)
            .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }

  /**
   * Busca un colaborador por su id de usuario.
   *
   * @param idUsuario Id del usuario del colaborador a buscar.
   *
   * @return Un Optional con el colaborador encontrado, o vacío si no se encontró.
   */

  public Optional<Colaborador> buscarPorIdUsuario(Long idUsuario) {
    String query = "SELECT c FROM Colaborador c WHERE c.usuario.id = :idUsuario";
    return entityManager().createQuery(query, Colaborador.class)
        .setParameter("idUsuario", idUsuario)
        .getResultList()
        .stream()
        .findFirst();
  }

}
