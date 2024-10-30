package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Direccion;

/**
 * Repositorio para los Direcciones.
 */

public class DireccionesRepository extends GenericRepository {

  public void guardar(Direccion direccion) {
    super.guardar(direccion);
  }

  public void modificar(Direccion direccion) {
    super.modificar(direccion);
  }

  public void eliminarFisico(Direccion direccion) {
    super.eliminarFisico(direccion);
  }

  public void eliminar(Direccion direccion) {
    super.eliminar(direccion);
  }

  /**
   * Busca un direccion por su id.
   *
   * @param id Id del direccion a buscar.
   *
   * @return Un Optional con el direccion encontrado, o vacío si no se encontró.
   */

  public Optional<Direccion> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es nulo");
    }
    return super.buscarPorId(id, Direccion.class);
  }

  public List<Direccion> buscarTodos() {
    return super.buscarTodos(Direccion.class);
  }

  /** Busca una direccion por su latitud y longitud.
   *
   * @param latitud latitud a buscar.
   * @param longitud longitud a buscar.
   * @return Un Optional con la direccion encontrada, o vacío si no se encontró.
   */

  public Optional<Direccion> buscarPorLatLong(Float latitud, Float longitud) {
    String query =
        "SELECT d FROM Direccion d WHERE d.latitud =: latitud AND d.longitud =: longitud";
    List<Direccion> results = entityManager().createQuery(query, Direccion.class)
        .setParameter("latitud", latitud)
        .setParameter("longitud", longitud)
        .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }
}

