package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.repositories.interfaces.InterfaceTarjetasVulnerablesRepository;

/**
 * Repositorio de tarjetas de vulnerables.
 */

public class TarjetasVulnerablesRepository implements InterfaceTarjetasVulnerablesRepository {

  /**
   * Guarda una tarjeta vulnerable.
   *
   * @param tarjetasVulnerables Tarjetas vulnerables a guardar.
   */

  public void guardar(TarjetaVulnerable... tarjetasVulnerables) {
    withTransaction(() -> {
      for (TarjetaVulnerable tarjetaVulerable : tarjetasVulnerables) {
        entityManager().persist(tarjetaVulerable);
      }
    });
  }

  public void guardar(TarjetaVulnerable tarjetaVulnerable) {
    withTransaction(() -> entityManager().persist(tarjetaVulnerable));
  }

  public void modificar(TarjetaVulnerable tarjetaVulnerable) {
    entityManager().merge(tarjetaVulnerable);
  }

  public void eliminarFisico(TarjetaVulnerable tarjetaVulnerable) {
    entityManager().remove(tarjetaVulnerable);
  }

  public void eliminar(TarjetaVulnerable tarjetaVulnerable) {
    tarjetaVulnerable.setActivo(false);
    entityManager().merge(tarjetaVulnerable);
  }

  public Optional<TarjetaVulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(TarjetaVulnerable.class, id));
  }

  /**
   * Busca todas las tarjetas vulnerables.
   *
   * @return Lista de tarjetas vulnerables.
   */

  @SuppressWarnings("unchecked")
  public List<TarjetaVulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + TarjetaVulnerable.class.getName())
        .getResultList();
  }
}
