package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;

/**
 * Repositorio para las Tarjetas de Colaborador.
 */

public class TarjetasColaboradoresRepository extends GenericRepository {

  public void guardar(TarjetaColaborador tarjetaColaborador) {
    super.guardar((tarjetaColaborador));
  }

  public void modificar(TarjetaColaborador tarjetaColaborador) {
    super.modificar(tarjetaColaborador);
  }

  public void eliminarFisico(TarjetaColaborador tarjetaColaborador) {
    super.eliminarFisico(tarjetaColaborador);
  }

  public void eliminar(TarjetaColaborador tarjetaColaborador) {
    super.eliminar(tarjetaColaborador);
  }

  /**
   * Busca una tarjeta por su id.
   *
   * @param id Id del tarjeta a buscar.
   *
   * @return Un Optional con la tarjeta encontrada, o vacío si no se encontró.
   */

  public Optional<TarjetaColaborador> buscarPorId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("id to load is required for loading");
    }
    return super.buscarPorId(id, TarjetaColaborador.class);
  }

  public List<TarjetaColaborador> buscarTodos() {
    return super.buscarTodos(TarjetaColaborador.class);
  }

  /**
   * Busca una tarjeta por su UUID.
   *
   * @param tarjeta UUID de la tarjeta a buscar.
   * @return Un Optional con la tarjeta encontrada, o vacío si no se encontró.
   */

  public Optional<TarjetaColaborador> buscarPorUuid(String tarjeta) {
    String query = "SELECT tc FROM TarjetaColaborador tc WHERE tc.codigo =: tarjeta";
    return entityManager().createQuery(query, TarjetaColaborador.class)
        .setParameter("tarjeta", tarjeta)
        .getResultList()
        .stream()
        .findFirst();
  }

  /**
   * Busca una tarjeta por su id de colaborador.
   *
   * @param idColaborador Id del colaborador de la tarjeta a buscar.
   *
   * @return Un Optional con la tarjeta encontrada, o vacío si no se encontró.
   */

  public Optional<TarjetaColaborador> buscarPorIdColaborador(Long idColaborador) {
    String query = "SELECT tc FROM TarjetaColaborador tc WHERE tc.colaborador.id = :idColaborador";
    return entityManager().createQuery(query, TarjetaColaborador.class)
        .setParameter("idColaborador", idColaborador)
        .getResultList()
        .stream()
        .findFirst();
  }
}
