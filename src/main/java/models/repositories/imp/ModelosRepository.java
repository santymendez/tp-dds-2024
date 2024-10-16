package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.Modelo;

/**
 * Repositorio de modelos.
 */

public class ModelosRepository extends GenericRepository {

  public void guardar(Modelo modelo) {
    super.guardar((modelo));
  }

  public void modificar(Modelo modelo) {
    super.modificar(modelo);
  }

  public void eliminarFisico(Modelo modelo) {
    super.eliminarFisico(modelo);
  }

  public void eliminar(Modelo modelo) {
    super.eliminar(modelo);
  }

  /**
   * Busca un modelo por su id.
   *
   * @param id el id del modelo.
   * @return el modelo.
   */

  public Optional<Modelo> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es nulo");
    }
    return super.buscarPorId(id, Modelo.class);
  }

  public List<Modelo> buscarTodos() {
    return super.buscarTodos(Modelo.class);
  }

  /**
   * Busca un modelo por nombre.
   *
   * @param nombre el nombre del modelo.
   * @return el modelo.
   */

  public Optional<Modelo> buscarPorNombre(String nombre) {
    List<Modelo> modelos = entityManager().createQuery(
            "SELECT m FROM Modelo m WHERE m.nombre = :nombre",
            Modelo.class
        )
        .setParameter("nombre", nombre)
        .getResultList();

    if (modelos.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(modelos.get(0));
    }
  }
}
