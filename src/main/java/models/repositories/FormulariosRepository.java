package models.repositories;

import java.util.Optional;
import models.entities.formulario.Formulario;
import models.repositories.imp.GenericRepository;

/**
 * Repositorio de formularios.
 */

public class FormulariosRepository extends GenericRepository {

  /**
   * Busca el último formulario.
   *
   * @return Formulario.
   */

  public Optional<Formulario> buscarUltimo() {
    return entityManager()
        .createQuery("SELECT f FROM Formulario f ORDER BY f.id DESC", Formulario.class)
        .setMaxResults(1)
        .getResultList()
        .stream()
        .findFirst();
  }
}
