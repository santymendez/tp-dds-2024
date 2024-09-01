package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Barrio;
import models.repositories.interfaces.InterfaceBarriosRepository;

/**
 * Repositorio de barrios.
 */

public class BarriosRepository implements InterfaceBarriosRepository {

  /**
   * Guarda un barrio en la base de datos.
   *
   * @param barrios Barrios a guardar.
   */

  public void guardar(Barrio... barrios) {
    withTransaction(() -> {
      for (Barrio barrio : barrios) {
        entityManager().persist(barrio);
      }
    });
  }

  public void guardar(Barrio barrio) {
    withTransaction(() -> entityManager().persist(barrio));
  }

  public void modificar(Barrio barrio) {
    entityManager().merge(barrio);
  }

  public void eliminarFisico(Barrio barrio) {
    entityManager().remove(barrio);
  }

  public void eliminar(Barrio barrio) {
    barrio.setActivo(false);
    entityManager().merge(barrio);
  }

  public Optional<Barrio> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Barrio.class, id));
  }

  /**
   * Busca todos los barrios.
   *
   * @return Lista de barrios.
   */

  @SuppressWarnings({"unchecked", "checkstyle:RequireEmptyLineBeforeBlockTagGroup"})
  public List<Barrio> buscarTodos() {
    return entityManager()
        .createQuery("from " + Barrio.class.getName())
        .getResultList();
  }
}
