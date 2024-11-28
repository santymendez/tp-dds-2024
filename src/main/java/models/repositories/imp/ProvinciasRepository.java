package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Provincia;

/**
 * Repositorio para las provincias.
 */

public class ProvinciasRepository extends GenericRepository {

  public void guardar(Provincia provincia) {
    super.guardar(provincia);
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


  /** Busca una provincia por su id.
   *
   * @param id id de la provincia a buscar.
   * @return Un Optional con la provincia encontrada, o vacío si no se encontró.
   */

  public Optional<Provincia> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es null");
    }
    return super.buscarPorId(id, Provincia.class);
  }

  public List<Provincia> buscarTodos() {
    return super.buscarTodos(Provincia.class);
  }
}
