package models.db;

import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Clase que representa al entity manager.
 */

public class EntityManagerHelper {

  private static EntityManagerFactory emf;

  private static ThreadLocal<EntityManager> threadLocal;

  static {
    try {
      emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
      threadLocal = new ThreadLocal<>();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static EntityManager entityManager() {
    return getEntityManager();
  }

  /**
   * Metodo que devuelve un entity manager para su uso.
   *
   * @return un Entity Manager.
   */

  public static EntityManager getEntityManager() {
    EntityManager manager = threadLocal.get();
    if (manager == null || !manager.isOpen()) {
      manager = emf.createEntityManager();
      threadLocal.set(manager);
    }
    return manager;
  }

  /**
   * a. SE desgarro cavani la puta madre
   */

  public static void closeEntityManager() {
    EntityManager em = threadLocal.get();
    threadLocal.set(null);
    em.close();
  }

  /**
   * a.
   */

  public static void beginTransaction() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    if (!tx.isActive()) {
      tx.begin();
    }
  }

  /**
   * a.
   */

  public static void commit() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    if (tx.isActive()) {
      tx.commit();
    }

  }

  /**
  * a.
   */

  public static void rollback() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    if (tx.isActive()) {
      tx.rollback();
    }
  }

  public static Query createQuery(String query) {
    return getEntityManager().createQuery(query);
  }

  public static void persist(Object o) {
    entityManager().persist(o);
  }

  /**
   * a.
   *
   * @param action a
   */

  public static void withTransaction(Runnable action) {
    withTransaction(() -> {
      action.run();
      return null;
    });
  }

  /**
   * a.
   *
   */

  public static <A> A withTransaction(Supplier<A> action) {
    beginTransaction();
    try {
      A result = action.get();
      commit();
      return result;
    } catch (Throwable e) {
      rollback();
      throw e;
    }
  }

}