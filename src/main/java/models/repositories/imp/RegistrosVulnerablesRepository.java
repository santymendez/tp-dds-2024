package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.repositories.PersistenceUnitSwitcher;
import models.repositories.interfaces.InterfaceRegistrosVulnerablesRepository;

/**
 * Repositorio para los registros de los vulnerables.
 */

public class RegistrosVulnerablesRepository implements InterfaceRegistrosVulnerablesRepository,
    WithSimplePersistenceUnit {

  @Override
  public void guardar(RegistroVulnerable... registrosVulnerables) {
    withTransaction(() -> {
      for (RegistroVulnerable registro : registrosVulnerables) {
        entityManager().persist(registro);
      }
    });
  }

  @Override
  public void guardar(RegistroVulnerable registroVulnerable) {
    withTransaction(() -> entityManager().persist(registroVulnerable));
  }

  @Override
  public void modificar(RegistroVulnerable registroVulnerable) {
    entityManager().merge(registroVulnerable);
  }

  @Override
  public void eliminarFisico(RegistroVulnerable registroVulnerable) {
    entityManager().remove(registroVulnerable);
  }

  @Override
  public void eliminar(RegistroVulnerable registroVulnerable) {
    registroVulnerable.setActivo(false);
    entityManager().merge(registroVulnerable);
  }

  @Override
  public Optional<RegistroVulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(RegistroVulnerable.class, id));
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<RegistroVulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + RegistroVulnerable.class.getName())
        .getResultList();
  }

  @Override
  public EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }

}
