package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Provincia;

/**
 * Repositorio para las provincias.
 */

public class ProvinciasRepository extends GenericRepository {

  public void guardar(Provincia... provincias) {
    super.guardar((Object) provincias);
  }

  public void guardar(Provincia provincia) {
    super.guardar((provincia));
  }

  public void modificar(Provincia provincia) {
    super.modificar(provincia);
  }

  public void eliminarFisico(Provincia provincia) {
    super.eliminarFisico(provincia);
  }

  public void eliminar(Provincia provincia) {
    super.eliminar(provincia);
  }

  /**
   * Busca un direccion por su id.
   *
   * @param id Id del direccion a buscar.
   *
   * @return Un Optional con el direccion encontrado, o vacío si no se encontró.
   */

  public Optional<Provincia> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, Provincia.class);
  }

  public List<Provincia> buscarTodos() {
    return super.buscarTodos(Provincia.class);
  }

  /** Busca una provincia por su nombre.
   *
   * @param nombreProvincia nombre de la provincia a buscar.
   * @return Un Optional con la direccion encontrada, o vacío si no se encontró.
   */

  public Optional<Provincia> buscarPorNombre(String nombreProvincia) {
    String query =
        "SELECT p FROM Provincia p WHERE p.nombreProvincia =: nombreProvincia";
    List<Provincia> results = entityManager().createQuery(query, Provincia.class)
        .setParameter("nombreProvincia", nombreProvincia)
        .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }
}
