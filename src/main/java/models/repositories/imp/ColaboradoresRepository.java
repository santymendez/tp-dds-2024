package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.interfaces.InterfaceColaboradoresRepository;

/**
 * Repositorio para los Colaboradores.
 */

@Getter
public class ColaboradoresRepository implements InterfaceColaboradoresRepository,
        WithSimplePersistenceUnit {

  /**
   * Guarda uno o más colaboradores en la base de datos.
   *
   * @param colaboradores colaboradores a guardar.
   */

  public void guardar(Colaborador... colaboradores) {
    withTransaction(() -> {
      for (Colaborador colaborador : colaboradores) {
        entityManager().persist(colaborador);
      }
    });
  }

  /**
   * Guarda un colaborador en la base de datos.
   *
   * @param colaborador colaborador a guardar.
   */

  public void guardar(Colaborador colaborador) {
    withTransaction(() -> entityManager().persist(colaborador));
  }

  /**
   * Modifica un colaborador en la base de datos.
   *
   * @param colaborador colaborador a modificar.
   */

  public void modificar(Colaborador colaborador) {
    withTransaction(() -> {
      entityManager().merge(colaborador);
    });
  }

  public void eliminarFisico(Colaborador colaborador) {
    entityManager().remove(colaborador);
  }

  public void eliminar(Colaborador colaborador) {
    colaborador.setActivo(false);
    entityManager().merge(colaborador);
  }

  public Optional<Colaborador> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Colaborador.class, id));
  }

  /**
   * Busca un colaborador por su número de documento.
   *
   * @param numeroDocumento Número de documento del colaborador a buscar.
   *
   * @return Un Optional con el colaborador encontrado, o vacío si no se encontró.
   */
  public Optional<Colaborador> buscarPorDocumento(Integer numeroDocumento) {
    String query = "SELECT c FROM Colaborador c WHERE c.documento.nroDocumento =: numeroDocumento";
    List<Colaborador> results = entityManager().createQuery(query, Colaborador.class)
            .setParameter("numeroDocumento", numeroDocumento)
            .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }

  /**
   * Busca todos los colaboradores existentes en la base de datos.
   *
   * @return una lista con todos los colaboradores.
   */
  @SuppressWarnings("unchecked")
  public List<Colaboracion> buscarTodos() {
    return entityManager()
        .createQuery("from " + Colaborador.class.getName())
        .getResultList();
  }
}
