package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.formulario.Formulario;
import models.repositories.interfaces.InterfaceFormulariosRepository;

/**
 * Repositorio para los formularios.
 */

public class FormulariosRepository implements InterfaceFormulariosRepository {

  /**
   * Guarda un formulario en la base de datos.
   *
   * @param formularios Formularios a guardar.
   */

  public void guardar(Formulario... formularios) {
    withTransaction(() -> {
      for (Formulario formulario : formularios) {
        entityManager().persist(formulario);
      }
    });
  }

  public void guardar(Formulario formulario) {
    withTransaction(() -> entityManager().persist(formulario));
  }

  public void modificar(Formulario formulario) {
    entityManager().merge(formulario);
  }

  public void eliminarFisico(Formulario formulario) {
    entityManager().remove(formulario);
  }

  public void eliminar(Formulario formulario) {
    formulario.setActivo(false);
    entityManager().merge(formulario);
  }

  public Optional<Formulario> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Formulario.class, id));
  }

  /**
   * Busca todos los formularios en la base de datos.
   *
   * @return Lista con todos los formularios.
   */

  @SuppressWarnings("unchecked")
  public List<Formulario> buscarTodos() {
    return entityManager()
        .createQuery("from " + Formulario.class.getName())
        .getResultList();
  }
}
