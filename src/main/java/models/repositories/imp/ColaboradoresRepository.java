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
public class ColaboradoresRepository implements InterfaceColaboradoresRepository, WithSimplePersistenceUnit {

  public void guardar(Colaborador... colaboradores) {
    withTransaction(() -> {
      for (Colaborador colaborador : colaboradores) {
        entityManager().persist(colaborador);
      }
    });
  }

  public void guardar(Colaborador colaborador) {
    withTransaction(() -> {
      entityManager().persist(colaborador);
    });
  }

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

  public Optional<Colaborador> buscarPorDocumento(Integer numeroDocumento) {
    List<Colaborador> results = entityManager().createQuery("SELECT c FROM Colaborador c WHERE c.documento.nroDocumento = :numeroDocumento", Colaborador.class)
            .setParameter("numeroDocumento", numeroDocumento)
            .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(results.get(0));
    }
  }

  @SuppressWarnings("unchecked")
  public List<Colaboracion> buscarTodos() {
    return entityManager()
        .createQuery("from " + Colaborador.class.getName())
        .getResultList();
  }
}
