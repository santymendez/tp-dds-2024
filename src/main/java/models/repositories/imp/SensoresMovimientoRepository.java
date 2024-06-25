package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.Heladera;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;


/**
 * Repositorio para los Sensores de Movimiento.
 */

public class SensoresMovimientoRepository {
  private List<SensorMovimiento> sensores;
  private static SensoresMovimientoRepository instance;


  /**
   * Busca un sensor por ID.
   *
   * @param id Parametro de busqueda.
   * @return El sensor si existe.
   */

  public Optional<SensorMovimiento> buscar(int id) {
    return sensores.stream()
        .filter(s -> s.getId().equals(id))
        .findFirst();
  }


  /**
   * Singleton para el Repositorio de Sensores de Movimiento.
   *
   * @return Instancia del Repositorio.
   */

  public static SensoresMovimientoRepository getInstance() {
    if (instance == null) {
      instance = new SensoresMovimientoRepository();
    }
    return instance;
  }

  public void guardar(SensorMovimiento sensor) {
    this.sensores.add(sensor);
  }
}


