package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.db.Persistente;
import models.repositories.PersistenciaSimple;

/**
 * Repositorio para persistir las entidades de dominio.
 */

public class GenericRepository implements PersistenciaSimple {

  /**
   * Guarda un objeto en la base de datos.
   * (CUIDADO!!! por motivos desconocidos da errores inesperados)
   *
   * @param objetos Objetos a guardar.
   */

  public void guardar(Object ... objetos) {
    withTransaction(() -> {
      for (Object objeto : objetos) {
        this.guardarModificar(objeto);
      }
    });
  }

  public void guardar(Object objeto) {
    withTransaction(() -> this.guardarModificar(objeto));
  }

  public void modificar(Object objeto) {
    withTransaction(() -> entityManager().merge(objeto));
  }

  /**
   * Guarda o modifica un objeto en la base de datos.
   *
   * @param objeto Objeto a guardar o modificar.
   */

  public void guardarModificar(Object objeto) {

    if (entityManager().contains(objeto)) {

      entityManager().merge(objeto);

    } else {

      entityManager().persist(objeto);

    }
  }

  public void eliminarFisico(Object objeto) {
    withTransaction(() -> entityManager().remove(objeto));
  }

  /**
   * Elimina un objeto de manera logica de la base de datos.
   *
   * @param objeto Objeto a eliminar.
   */

  public void eliminar(Object objeto) {

    if (objeto instanceof Persistente persistente) {
      persistente.setActivo(false);
      this.modificar(persistente);
    } else {
      throw new IllegalArgumentException("El objeto no es una instancia de Persistente");
    }
  }

  public <T> Optional<T> buscarPorId(Long id, Class<T> clase) {
    return Optional.ofNullable(entityManager().find(clase, id));
  }

  /**
   * Busca todos los objetos de una clase.
   *
   * @param clase Clase de los objetos a buscar.
   * @param <T> Tipo de los objetos a buscar.
   * @return Lista de objetos.
   */

  public <T> List<T> buscarTodos(Class<T> clase) {
    return entityManager()
        .createQuery("from " + clase.getName(), clase)
        .getResultList();
  }

}
