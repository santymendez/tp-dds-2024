package models.db;

import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Esta clase ayuda a manejar la persistencia de objetos utilizando el EntityManager
 * de JPA, proporcionando métodos para manejar transacciones, consultas y la
 * configuración de la unidad de persistencia.
 */
public class EntityManagerHelper {

  /**
   * Fábrica para crear instancias de EntityManager.
   */
  private static EntityManagerFactory emf;

  /**
   * Variable ThreadLocal que almacena el EntityManager por hilo.
   */
  private static ThreadLocal<EntityManager> threadLocal;

  static {
    threadLocal = new ThreadLocal<>();
    configure("simple-persistence-unit");
  }

  /**
   * Configura la unidad de persistencia utilizada por el EntityManagerFactory.
   * Si hay una unidad de persistencia ya abierta, la cierra antes de crear una nueva.
   *
   * @param persistenceUnitName El nombre de la unidad de persistencia a configurar.
   */
  public static void configure(String persistenceUnitName) {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
    try {
      emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Devuelve el EntityManager asociado al hilo actual.
   * Si no existe un EntityManager, crea uno nuevo.
   *
   * @return El EntityManager asociado al hilo actual.
   */
  public static EntityManager entityManager() {
    return getEntityManager();
  }

  /**
   * Obtiene o crea un EntityManager asociado al hilo actual.
   * Si no existe un EntityManager para el hilo actual, crea uno nuevo y lo asocia.
   *
   * @return El EntityManager asociado al hilo actual.
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
   * Cierra el EntityManager asociado al hilo actual, si está abierto,
   * y lo elimina de la variable threadLocal.
   */
  public static void closeEntityManager() {
    EntityManager em = threadLocal.get();
    threadLocal.set(null);
    if (em != null && em.isOpen()) {
      em.close();
    }
  }

  /**
   * Inicia una transacción en el EntityManager asociado al hilo actual.
   * Si ya hay una transacción activa, no hace nada.
   */
  public static void beginTransaction() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    if (!tx.isActive()) {
      tx.begin();
    }
  }

  /**
   * Realiza un commit de la transacción actual en el EntityManager asociado
   * al hilo actual, si la transacción está activa.
   */
  public static void commit() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    if (tx.isActive()) {
      tx.commit();
    }
  }

  /**
   * Realiza un rollback de la transacción actual en el EntityManager asociado
   * al hilo actual, si la transacción está activa.
   */
  public static void rollback() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    if (tx.isActive()) {
      tx.rollback();
    }
  }

  /**
   * Crea y devuelve una consulta JPQL utilizando el EntityManager
   * asociado al hilo actual.
   *
   * @param query La cadena JPQL para crear la consulta.
   * @return Una instancia de Query creada a partir de la cadena JPQL.
   */
  public static Query createQuery(String query) {
    return getEntityManager().createQuery(query);
  }

  /**
   * Persiste una entidad en la base de datos utilizando el EntityManager
   * asociado al hilo actual.
   *
   * @param o La entidad que se va a persistir.
   */
  public static void persist(Object o) {
    entityManager().persist(o);
  }

  /**
   * Ejecuta una acción dentro de una transacción.
   * Se encarga de iniciar, cometer o hacer rollback de la transacción según sea necesario.
   *
   * @param action La acción a ejecutar dentro de la transacción.
   */
  public static void withTransaction(Runnable action) {
    withTransaction(() -> {
      action.run();
      return null;
    });
  }

  /**
   * Ejecuta una acción dentro de una transacción, devolviendo un valor.
   * Se encarga de iniciar, cometer o hacer rollback de la transacción según sea necesario.
   *
   * @param <A> El tipo del valor de retorno.
   * @param action La acción a ejecutar dentro de la transacción.
   * @return El valor devuelto por la acción ejecutada.
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

