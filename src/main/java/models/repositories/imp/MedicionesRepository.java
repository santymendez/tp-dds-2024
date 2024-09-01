package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.MedicionSensor;
import models.repositories.interfaces.InterfaceMedicionesRepository;

/**
 * Repositorio para las Mediciones.
 */

public class MedicionesRepository implements InterfaceMedicionesRepository {

  /**
   * Guarda una o varias mediciones.
   *
   * @param mediciones una o varias mediciones.
   */
  public void guardar(MedicionSensor... mediciones) {
    withTransaction(() -> {
      for (MedicionSensor medicion : mediciones) {
        entityManager().persist(medicion);
      }
    });
  }

  /** Guarda una medicion en la base de datos.
   *
   * @param medicionSensor una medicion.
   */

  public void guardar(MedicionSensor medicionSensor) {
    withTransaction(() -> entityManager().persist(medicionSensor));
  }

  /**
   * modifica una medicion en la base de datos.
   *
   * @param medicionSensor una medicion.
   */

  public void modificar(MedicionSensor medicionSensor) {
    entityManager().merge(medicionSensor);
  }

  public void eliminarFisico(MedicionSensor medicionSensor) {
    entityManager().remove(medicionSensor);
  }

  public void eliminar(MedicionSensor medicionSensor) {
    medicionSensor.setActivo(false);
    entityManager().merge(medicionSensor);
  }

  public Optional<MedicionSensor> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(MedicionSensor.class, id));
  }

  /**
   * busca todas las meciones del sensor que estan en la base de datos.
   *
   * @return una lista de mediciones.
   */

  @SuppressWarnings("unchecked")
  public List<MedicionSensor> buscarTodos() {
    return entityManager()
        .createQuery("from " + MedicionSensor.class.getName())
        .getResultList();
  }
}
