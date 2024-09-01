package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.repositories.interfaces.InterfaceDireccionesRepository;

/**
 * Clase repositorio de direcciones.
 */

public class DireccionesRepository implements InterfaceDireccionesRepository {

  /**
   * Guarda una o varias direcciones en la base de datos.
   *
   * @param direcciones Direcciones a guardar.
   */

  public void guardar(Direccion... direcciones) {
    withTransaction(() -> {
      for (Direccion direccion : direcciones) {
        entityManager().persist(direccion);
      }
    });
  }

  public void guardar(Direccion direccion) {
    withTransaction(() -> entityManager().persist(direccion));
  }

  public void modificar(Direccion direccion) {
    entityManager().merge(direccion);
  }

  public void eliminarFisico(Direccion direccion) {
    entityManager().remove(direccion);
  }

  public void eliminar(Direccion direccion) {
    direccion.setActivo(false);
    entityManager().merge(direccion);
  }

  public Optional<Direccion> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Direccion.class, id));
  }

  /**
   * Busca todas las direcciones en la base de datos.
   *
   * @return Lista de direcciones.
   */

  @SuppressWarnings("unchecked")
  public List<Direccion> buscarTodos() {
    return entityManager()
        .createQuery("from " + Direccion.class.getName())
        .getResultList();
  }
}
