package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Ciudad;

/**
 * Repositorio para las ciudades.
 */

public class CiudadesRepository extends GenericRepository {

  public void guardar(Ciudad ciudad) {
    super.guardar(ciudad);
  }

  public void modificar(Ciudad ciudad) {
    super.modificar(ciudad);
  }

  public void eliminarFisico(Ciudad ciudad) {
    super.eliminarFisico(ciudad);
  }

  public void eliminar(Ciudad ciudad) {
    super.eliminar(ciudad);
  }


  /** Busca una provincia por su id.
   *
   * @param id id de la provincia a buscar.
   * @return Un Optional con la provincia encontrada, o vacío si no se encontró.
   */

  public Optional<Ciudad> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es null");
    }
    return super.buscarPorId(id, Ciudad.class);
  }

  public List<Ciudad> buscarTodos() {
    return super.buscarTodos(Ciudad.class);
  }
}
