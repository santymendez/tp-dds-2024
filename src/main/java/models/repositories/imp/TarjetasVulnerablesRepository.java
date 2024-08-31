package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceTarjetasVulnerablesRepository;

/**
 * Repositorio de tarjetas de vulnerables.
 */

public class TarjetasVulnerablesRepository implements InterfaceTarjetasVulnerablesRepository,
    WithSimplePersistenceUnit {

  @Override
  public void guardar(TarjetaVulnerable... tarjetasVulnerables) {
    withTransaction(() -> {
      for (TarjetaVulnerable tarjetaVulerable : tarjetasVulnerables) {
        entityManager().persist(tarjetaVulerable);
      }
    });
  }

  @Override
  public void guardar(TarjetaVulnerable tarjetaVulnerable) {
    withTransaction(() -> entityManager().persist(tarjetaVulnerable));
  }

  @Override
  public void modificar(TarjetaVulnerable tarjetaVulnerable) {
    entityManager().merge(tarjetaVulnerable);
  }

  @Override
  public void eliminarFisico(TarjetaVulnerable tarjetaVulnerable) {
    entityManager().remove(tarjetaVulnerable);
  }

  @Override
  public void eliminar(TarjetaVulnerable tarjetaVulnerable) {
    tarjetaVulnerable.setActivo(false);
    entityManager().merge(tarjetaVulnerable);
  }

  @Override
  public Optional<TarjetaVulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(TarjetaVulnerable.class, id));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<TarjetaVulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + TarjetaVulnerable.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }

}
