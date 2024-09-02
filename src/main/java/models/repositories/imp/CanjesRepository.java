package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.colaborador.canje.Canje;
import models.repositories.interfaces.InterfaceCanjesRepository;

/**
 * Repositorio para los Canjes.
 */

public class CanjesRepository implements InterfaceCanjesRepository {

  /**
   * Guarda los Canjes en la base de datos.
   *
   * @param canjes los Canjes a guardar.
   */

  public void guardar(Canje... canjes) {
    withTransaction(() -> {
      for (Canje canje : canjes) {
        entityManager().persist(canje);
      }
    });
  }

  public void guardar(Canje canje) {
    withTransaction(() -> entityManager().persist(canje));
  }

  public void modificar(Canje canje) {
    entityManager().merge(canje);
  }

  public void eliminarFisico(Canje canje) {
    canje.setActivo(false);
    entityManager().merge(canje);
  }

  public void eliminar(Canje canje) {
    entityManager().remove(canje);
  }

  public Optional<Canje> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Canje.class, id));
  }

  /**
   * Busca todos los Canjes.
   *
   * @return Lista de Canjes.
   */

  @SuppressWarnings("unchecked")
  public List<Canje> buscarTodos() {
    return entityManager()
        .createQuery("from " + Canje.class.getName())
        .getResultList();
  }
}
