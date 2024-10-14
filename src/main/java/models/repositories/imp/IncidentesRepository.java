package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;

/**
 * Repositorio de incidentes.
 */

public class IncidentesRepository extends GenericRepository {
  public void guardar(Incidente incidente) {
    super.guardar((incidente));
  }

  public void modificar(Incidente incidente) {
    super.modificar(incidente);
  }

  public void eliminarFisico(Incidente incidente) {
    super.eliminarFisico(incidente);
  }

  public void eliminar(Incidente incidente) {
    super.eliminar(incidente);
  }

  /**
   * Busca un incidente por su id.
   *
   * @param id Id del incidente a buscar.
   *
   * @return Un Optional con el incidente encontrado, o vacío si no se encontró.
   */

  public Optional<Incidente> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, Incidente.class);
  }

  public List<Incidente> buscarTodos() {
    return super.buscarTodos(Incidente.class);
  }

  /**
   * Devuelve todos los incidentes de tipo Alerta.
   *
   * @return todas las alertas.
   */

  public List<Incidente> buscarAlertas() {
    return
        this.buscarTodos()
            .stream()
            .filter(i -> i.getTipo().equals(TipoIncidente.ALERTA))
            .toList();
  }

  /**
   * Devuelve todos los incidentes de tipo Mantenimiento.
   *
   * @param idHeladera id de la heladera.
   * @return todos los mantenimientos.
   */

  public List<Incidente> buscarPorHeladera(Long idHeladera) {
    return entityManager()
        .createQuery("FROM Incidente i WHERE i.heladera.id = :idHeladera", Incidente.class)
        .setParameter("idHeladera", idHeladera)
        .getResultList();
  }
}
