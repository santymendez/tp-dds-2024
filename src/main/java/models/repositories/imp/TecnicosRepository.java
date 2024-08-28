package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.direccion.Ciudad;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.interfaces.InterfaceTecnicosRepository;

/**
 * Repositorio para los Tecnicos.
 */

@Getter
public class TecnicosRepository implements InterfaceTecnicosRepository, WithSimplePersistenceUnit {

  /**
   * guarda tecnicos en la base de datos.
   *
   * @param tecnicos unos tecnicos.
   */

  public void guardar(Tecnico... tecnicos) {
    withTransaction(() -> {
      for (Tecnico tecnico : tecnicos) {
        entityManager().persist(tecnico);
      }
    });
  }

  /**
   *  guarda un tecnico en la base de datos.
   *
   * @param tecnico un tecnico.
   */

  public void guardar(Tecnico tecnico) {
    withTransaction(() -> entityManager().persist(tecnico));
  }

  /**
   * modifica un tecnico en la base de datos.
   *
   * @param tecnico un tecnico.
   */

  public void modificar(Tecnico tecnico) {
    withTransaction(() -> {
      entityManager().merge(tecnico);
    });
  }

  public void eliminarFisico(Tecnico tecnico) {
    entityManager().remove(tecnico);
  }

  public void eliminar(Tecnico tecnico) {
    tecnico.setActivo(false);
    entityManager().merge(tecnico);
  }

  public Optional<Tecnico> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Tecnico.class, id));
  }


  /** Busca todos los tecnicos.
   *
   * @return una lista con todos los tecnicos.
   */
  @SuppressWarnings("unchecked")
  public List<Tecnico> buscarTodos() {
    return entityManager()
        .createQuery("from " + Tecnico.class.getName())
        .getResultList();
  }

  /**
   * Busca Tecnicos en el Repositorio.
   *
   * @param ciudad Parámetro de búsqueda.
   * @return Una lista de Técnicos si llegasen a existir, en otro caso una lista vacía.
   */

  public List<Tecnico> buscarPorCiudad(Ciudad ciudad) {
    return entityManager()
        .createQuery("FROM Tecnico tecnico WHERE tecnico.areaDeCobertura = :ciudad", Tecnico.class)
        .setParameter("ciudad", ciudad)
        .getResultList();
  }
}
