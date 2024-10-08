package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

/**
 * Repositorio para las TarjetasVulnerables.
 */

public class TarjetasVulnerablesRepository extends GenericRepository {

  public void guardar(TarjetaVulnerable tarjetaVulnerable) {
    super.guardar((tarjetaVulnerable));
  }

  public void modificar(TarjetaVulnerable tarjetaVulnerable) {
    super.modificar(tarjetaVulnerable);
  }

  public void eliminarFisico(TarjetaVulnerable tarjetaVulnerable) {
    super.eliminarFisico(tarjetaVulnerable);
  }

  public void eliminar(TarjetaVulnerable tarjetaVulnerable) {
    super.eliminar(tarjetaVulnerable);
  }

  /**
   * Busca un direccion por su id.
   *
   * @param id Id del direccion a buscar.
   *
   * @return Un Optional con el direccion encontrado, o vacío si no se encontró.
   */

  public Optional<TarjetaVulnerable> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, TarjetaVulnerable.class);
  }

  public List<TarjetaVulnerable> buscarTodos() {
    return super.buscarTodos(TarjetaVulnerable.class);
  }

  /**
   * Busca una tarjeta por su UUID.
   *
   * @param tarjeta UUID de la tarjeta a buscar.
   * @return Un Optional con la tarjeta encontrada, o vacío si no se encontró.
   */

  public Optional<TarjetaVulnerable> buscarPorUuid(String tarjeta) {
    String query = "SELECT tv FROM TarjetaVulnerable tv WHERE tv.codigo =: tarjeta";
    return entityManager().createQuery(query, TarjetaVulnerable.class)
        .setParameter("tarjeta", tarjeta)
        .getResultList()
        .stream()
        .findFirst();
  }
}
