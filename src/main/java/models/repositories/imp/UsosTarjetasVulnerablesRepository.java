package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;

/**
 * Repositorio para los usos de las tarjetas de los vulnerables.
 */

public class UsosTarjetasVulnerablesRepository extends GenericRepository {

  public void guardar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    super.guardar(usoTarjetaVulnerable);
  }

  public void modificar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    super.modificar(usoTarjetaVulnerable);
  }

  public void eliminarFisico(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    super.eliminarFisico(usoTarjetaVulnerable);
  }

  public void eliminar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    super.eliminar(usoTarjetaVulnerable);
  }

  public Optional<UsoTarjetaVulnerable> buscarPorId(Long id) {
    return super.buscarPorId(id, UsoTarjetaVulnerable.class);
  }

  public List<UsoTarjetaVulnerable> buscarTodos() {
    return super.buscarTodos(UsoTarjetaVulnerable.class);
  }

  /**
   * Busca todos los Usos de Tarjetas de Vulnerables filtrando por Barrio.
   *
   * @param nombreBarrio El nombre del Barrio por el que se quiere filtrar.
   * @return una lista con los usos para ese barrio.
   */

  public List<UsoTarjetaVulnerable> buscarPorBarrio(String nombreBarrio) {
    String query = "SELECT u FROM UsoTarjetaVulnerable u "
        + "WHERE u.heladera.direccion.barrio.nombreBarrio =: nombreBarrio";
    return entityManager().createQuery(query, UsoTarjetaVulnerable.class)
        .setParameter("nombreBarrio", nombreBarrio)
        .getResultList();
  }
}
