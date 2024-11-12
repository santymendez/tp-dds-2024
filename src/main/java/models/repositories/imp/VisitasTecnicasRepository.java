package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.direccion.Provincia;
import models.entities.personas.tecnico.VisitaTecnica;

/**
 * Repositorio de visitas tecnicas.
 */

public class VisitasTecnicasRepository extends GenericRepository {

  public void guardar(VisitaTecnica visitaTecnica) {
    super.guardar(visitaTecnica);
  }

  public void modificar(VisitaTecnica visitaTecnica) {
    super.modificar(visitaTecnica);
  }

  public void eliminarFisico(VisitaTecnica visitaTecnica) {
    super.eliminarFisico(visitaTecnica);
  }

  public void eliminar(VisitaTecnica visitaTecnica) {
    super.eliminar(visitaTecnica);
  }

  /**
   * Busca un visitaTecnica por su id.
   *
   * @param id Id del visitaTecnica a buscar.
   * @return Un Optional con el visitaTecnica encontrado, o vacío si no se encontró.
   */

  public Optional<VisitaTecnica> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id es null");
    }
    return super.buscarPorId(id, VisitaTecnica.class);
  }

  public List<Provincia> buscarTodos() {
    return super.buscarTodos(Provincia.class);
  }

  /**
   * Busca un visitaTecnica las visitas tecnicas en las que se resolvio el incidente.
   *
   * @return Un Optional con la visita Tecnica encontrada, o vacío si no se encontró.
   */

  public List<VisitaTecnica> buscarResueltos() {
    return entityManager()
        .createQuery(
            "SELECT v FROM VisitaTecnica v WHERE v.incidenteSolucionado = true",
            VisitaTecnica.class
        )
        .getResultList();
  }

}
