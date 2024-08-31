package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceUsosTarjetasVulnerablesRepository;

/**
 * Repositorio para los usos de las tarjetas de los vulnerables.
 */

public class UsosTarjetasVulnerablesRepository implements
    InterfaceUsosTarjetasVulnerablesRepository, WithSimplePersistenceUnit {


  @Override
  public void guardar(UsoTarjetaVulnerable... usosTarjetasVulnerables) {
    withTransaction(() -> {
      for (UsoTarjetaVulnerable usoTarjetaVulnerable : usosTarjetasVulnerables) {
        entityManager().persist(usoTarjetaVulnerable);
      }
    });
  }

  @Override
  public void guardar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    withTransaction(() -> entityManager().persist(usoTarjetaVulnerable));
  }

  @Override
  public void modificar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    withTransaction(() -> entityManager().persist(usoTarjetaVulnerable));
  }

  @Override
  public void eliminarFisico(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    entityManager().remove(usoTarjetaVulnerable);
  }

  @Override
  public void eliminar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    usoTarjetaVulnerable.setActivo(false);
    entityManager().merge(usoTarjetaVulnerable);
  }

  @Override
  public Optional<UsoTarjetaVulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(UsoTarjetaVulnerable.class, id));
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<UsoTarjetaVulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + UsoTarjetaVulnerable.class.getName())
        .getResultList();
  }

}
