package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;
import models.repositories.interfaces.InterfaceUsosTarjetasVulnerablesRepository;

/**
 * Repositorio para los usos de las tarjetas de los vulnerables.
 */

public class UsosTarjetasVulnerablesRepository
    implements InterfaceUsosTarjetasVulnerablesRepository {

  /** Guarda uno o varios usos de tarjetas de vulnerables.
   *
   * @param usosTarjetasVulnerables uno o varios usos de tarjetas de vulnerables.
   */

  public void guardar(UsoTarjetaVulnerable... usosTarjetasVulnerables) {
    withTransaction(() -> {
      for (UsoTarjetaVulnerable usoTarjetaVulnerable : usosTarjetasVulnerables) {
        entityManager().persist(usoTarjetaVulnerable);
      }
    });
  }

  public void guardar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    withTransaction(() -> entityManager().persist(usoTarjetaVulnerable));
  }

  public void modificar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    withTransaction(() -> entityManager().persist(usoTarjetaVulnerable));
  }

  public void eliminarFisico(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    entityManager().remove(usoTarjetaVulnerable);
  }

  public void eliminar(UsoTarjetaVulnerable usoTarjetaVulnerable) {
    usoTarjetaVulnerable.setActivo(false);
    entityManager().merge(usoTarjetaVulnerable);
  }

  public Optional<UsoTarjetaVulnerable> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(UsoTarjetaVulnerable.class, id));
  }

  /**
   * Busca todos los usos de tarjetas de vulnerables.
   *
   * @return una lista con todos los usos de tarjetas de vulnerables.
   */

  @SuppressWarnings("unchecked")
  public List<UsoTarjetaVulnerable> buscarTodos() {
    return entityManager()
        .createQuery("from " + UsoTarjetaVulnerable.class.getName())
        .getResultList();
  }
}
