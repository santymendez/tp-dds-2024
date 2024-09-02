package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Ciudad;
import models.repositories.interfaces.InterfaceCiudadesRepository;

/**
 * Repositorio para las Ciudades.
 */

public class CiudadesRepository implements InterfaceCiudadesRepository {

  /**
   * Guarda las Ciudades en la base de datos.
   *
   * @param ciudades Las Ciudades a guardar.
   */

  public void guardar(Ciudad... ciudades) {
    withTransaction(() -> {
      for (Ciudad ciudad : ciudades) {
        entityManager().persist(ciudad);
      }
    });
  }

  public void guardar(Ciudad ciudad) {
    withTransaction(() -> entityManager().persist(ciudad));
  }

  public void modificar(Ciudad ciudad) {
    entityManager().merge(ciudad);
  }

  public void eliminarFisico(Ciudad ciudad) {
    ciudad.setActivo(false);
    entityManager().merge(ciudad);
  }

  public void eliminar(Ciudad ciudad) {
    entityManager().remove(ciudad);
  }

  public Optional<Ciudad> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Ciudad.class, id));
  }

  /**
   * Busca todas las Ciudaes.
   *
   * @return Lista de Ciudades.
   */

  @SuppressWarnings("unchecked")
  public List<Ciudad> buscarTodos() {
    return entityManager()
        .createQuery("from " + Ciudad.class.getName())
        .getResultList();
  }
}
