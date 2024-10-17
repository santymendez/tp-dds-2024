package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.personas.users.TipoRol;

/**
 * Repositorio de heladeras.
 */

public class HeladerasRepository extends GenericRepository {

  public void guardar(Heladera heladera) {
    super.guardar((heladera));
  }

  public void modificar(Heladera heladera) {
    super.modificar(heladera);
  }

  public void eliminarFisico(Heladera heladera) {
    super.eliminarFisico(heladera);
  }

  public void eliminar(Heladera heladera) {
    super.eliminar(heladera);
  }

  /**
   * Busca un direccion por su id.
   *
   * @param id Id del direccion a buscar.
   *
   * @return Un Optional con el direccion encontrado, o vacío si no se encontró.
   */

  public Optional<Heladera> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es nulo");
    }
    return super.buscarPorId(id, Heladera.class);
  }

  public List<Heladera> buscarTodos() {
    return super.buscarTodos(Heladera.class);
  }

  /**
   * Busca todas las heladeras activas.
   *
   * @return Lista de heladeras activas.
   */

  public List<Heladera> buscarActivas() {
    return entityManager()
        .createQuery(
            "SELECT h FROM Heladera h WHERE h.estadoActual.estado = :activa",
            Heladera.class
        )
        .setParameter("activa", TipoEstado.ACTIVA)
        .getResultList();
  }

  public List<Heladera> buscarInactivas() {
    return this.buscarTodos().stream()
        .filter(h -> !h.getEstadoActual().getEstado().equals(TipoEstado.ACTIVA)).toList();
  }
}
