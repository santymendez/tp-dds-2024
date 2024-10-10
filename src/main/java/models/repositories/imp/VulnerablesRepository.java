package models.repositories.imp;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import models.entities.personas.vulnerable.Vulnerable;

public class VulnerablesRepository extends GenericRepository {

  public void guardar(Vulnerable vulnerable) {
    super.guardar((vulnerable));
  }

  public void modificar(Vulnerable vulnerable) {
    super.modificar(vulnerable);
  }

  public void eliminarFisico(Vulnerable vulnerable) {
    super.eliminarFisico(vulnerable);
  }

  public void eliminar(Vulnerable vulnerable) {
    super.eliminar(vulnerable);
  }

  /**
   * Busca un vulnerable por su id.
   *
   * @param id Id del direccion a buscar.
   *
   * @return Un Optional con el vulnerable encontrado, o vacío si no se encontró.
   */

  public Optional<Vulnerable> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, Vulnerable.class);
  }

  public List<Vulnerable> buscarTodos() {
    return super.buscarTodos(Vulnerable.class);
  }

  public Optional<Vulnerable> buscarPorDocumentoYFechaNacimiento(Integer documento, LocalDate fechaNacimiento){
    String query = "SELECT v FROM Vulnerable v WHERE v.documento.nroDocumento = :documento AND v.fechaNacimiento = :cumple";
    List<Vulnerable> results = entityManager().createQuery(query, Vulnerable.class)
        .setParameter("documento", documento)
        .setParameter("cumple", fechaNacimiento)
        .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }
}
