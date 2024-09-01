package models.repositories.interfaces;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;
import models.db.PersistenceUnitSwitcher;

/**
 * Interfaz que define un repositorio con persistencia simple.
 */

public interface PersistenciaSimple extends WithSimplePersistenceUnit {

  default EntityManager entityManager() {
    return PersistenceUnitSwitcher.getEntityManager();
  }
}
