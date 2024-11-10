package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.colaborador.canje.Canje;

/**
 * Repositorio de canjes.
 */

public class CanjesRepository extends GenericRepository {

  public void guardar(Canje canje) {
    super.guardar(canje);
  }

  public void modificar(Canje canje) {
    super.modificar(canje);
  }

  public void eliminarFisico(Canje canje) {
    super.eliminarFisico(canje);
  }

  public void eliminar(Canje canje) {
    super.eliminar(canje);
  }

  /**
   * Busca un canje por su id.
   *
   * @param id Id del canje a buscar.
   *
   * @return Un Optional con el canje encontrado, o vacío si no se encontró.
   */

  public Optional<Canje> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es nulo");
    }
    return super.buscarPorId(id, Canje.class);
  }

  public List<Canje> buscarTodos() {
    return super.buscarTodos(Canje.class);
  }

  /**
   * Busca los canjes de un colaborador.
   *
   * @param idColaborador Id del colaborador.
   *
   * @return Lista de canjes del colaborador.
   */

  public List<Canje> buscarCanjesDe(Long idColaborador) {
    if (idColaborador == null) {
      throw new IllegalArgumentException("El id del colaborador es nulo");
    }
    String query = "SELECT c FROM Canje c WHERE c.colaborador.id = :idColaborador";
    return entityManager().createQuery(query, Canje.class)
        .setParameter("idColaborador", idColaborador)
        .getResultList();
  }
}
