package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.repositories.interfaces.InterfaceRegistrosVulnerablesRepository;

/**
 * Repositorio para los registros de los vulnerables.
 */

public class RegistrosVulnerablesRepository implements InterfaceRegistrosVulnerablesRepository {

  /**
   * Guarda un registro de vulnerable.
   *
   * @param registrosVulnerables Registros de vulnerables a guardar.
   */

  public void guardar(RegistroVulnerable... registrosVulnerables) {
    withTransaction(() -> {
      for (RegistroVulnerable registro : registrosVulnerables) {
        entityManager().persist(registro);
      }
    });
  }

  public void guardar(RegistroVulnerable registroVulnerable) {
    withTransaction(() -> entityManager().persist(registroVulnerable));
  }

  public void modificar(RegistroVulnerable registroVulnerable) {
    entityManager().merge(registroVulnerable);
  }

  public void eliminarFisico(RegistroVulnerable registroVulnerable) {
    entityManager().remove(registroVulnerable);
  }

  public void eliminar(RegistroVulnerable registroVulnerable) {
    registroVulnerable.setActivo(false);
    entityManager().merge(registroVulnerable);
  }

  public Optional<RegistroVulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(RegistroVulnerable.class, id));
  }

  /**
   * Busca todos los registros de vulnerables.
   *
   * @return Lista de registros de vulnerables.
   */

  @SuppressWarnings("unchecked")
  public List<RegistroVulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + RegistroVulnerable.class.getName())
        .getResultList();
  }
}
