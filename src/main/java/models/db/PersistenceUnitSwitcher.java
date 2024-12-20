package models.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta clase se encarga de cambiar la unidad de persistencia de la aplicación. Por defecto
 * utiliza la unidad "simple-persistence-unit" que usa memoria.
 * Ejemplo de uso:
 *     PersistenceUnitSwitcher.switchPersistenceUnit("database-persistence-unit");
 */

public class PersistenceUnitSwitcher {
  private static EntityManagerFactory entityManagerFactory = Persistence
      .createEntityManagerFactory("simple-persistence-unit");
  private static EntityManager entityManager = entityManagerFactory.createEntityManager();

  /**
   * Se encarga de cambiar la unidad de persistencia.
   *
   * @param persistenceUnitName Nombre de la unidad de persistencia a la que se desea cambiar.
   */

  public static void switchPersistenceUnit(String persistenceUnitName) {


    if (entityManager != null && entityManager.isOpen()) {

      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().commit();
      }

      entityManager.close();
    }

    if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
      entityManagerFactory.close();
    }

    entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
    entityManager = entityManagerFactory.createEntityManager();
  }

  /**
   * Se encarga de obtener el EntityManager.
   *
   *  @return EntityManager de la aplicación.
   */
  public static EntityManager getEntityManager() {

    if (entityManager == null || !entityManager.isOpen()) {
      throw new IllegalStateException("EntityManager no esta inicializado o esta cerrado.");
    }

    return entityManager;
  }
}