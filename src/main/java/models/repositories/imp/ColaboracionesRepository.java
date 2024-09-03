package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;

/**
 * Repositorio para las Colaboraciones.
 */

public class ColaboracionesRepository extends GenericRepository {

  public void guardar(Colaboracion ... colaboraciones) {
    super.guardar((Object) colaboraciones);
  }

  public void guardar(Colaboracion colaboracion) {
    super.guardar(colaboracion);
  }

  public void modificar(Colaboracion colaboracion) {
    super.modificar(colaboracion);
  }

  public void eliminarFisico(Colaboracion colaboracion) {
    super.eliminarFisico(colaboracion);
  }

  public void eliminar(Colaboracion colaboracion) {
    super.eliminar(colaboracion);
  }

  public Optional<Colaboracion> buscarPorId(Long id) {
    return super.buscarPorId(id, Colaboracion.class);
  }

  public List<Colaboracion> buscarTodos() {
    return super.buscarTodos(Colaboracion.class);
  }

  /**
   * busca todas las colaboraciones de ese tipo existentes en la base de datos.
   *
   * @param tipoColaboracion el tipo para comparar.
   * @return una lista con todas las colaboraciones que cumplen.
   */

  public List<Colaboracion> buscarPorTipo(TipoColaboracion tipoColaboracion) {
    return entityManager()
        .createQuery("FROM Colaboracion colab WHERE colab.tipoColaboracion = :tipo",
                Colaboracion.class)
        .setParameter("tipo", tipoColaboracion)
        .getResultList();
  }
}
