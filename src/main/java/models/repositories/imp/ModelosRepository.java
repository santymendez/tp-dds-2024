package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.Modelo;

/**
 * Repositorio de modelos.
 */

public class ModelosRepository extends GenericRepository {

  /**
   * Busca un modelo por nombre.
   *
   * @param nombre el nombre del modelo.
   * @return el modelo.
   */

  public Optional<Modelo> buscarPorNombre(String nombre) {
    List<Modelo> modelos = entityManager().createQuery(
            "SELECT m FROM Modelo m WHERE m.nombre = :nombre",
            Modelo.class
        )
        .setParameter("nombre", nombre)
        .getResultList();

    if (modelos.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(modelos.get(0));
    }
  }
}
