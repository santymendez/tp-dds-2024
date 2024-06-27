package models.repositories.heladera;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;


/**
 * Repositorio para los Sensores de Movimiento.
 */

public class SensoresMovimientoRepository implements InterfaceSensoresMovimientoRepository {
  private final List<SensorMovimiento> sensores;

  public SensoresMovimientoRepository() {
    this.sensores = new ArrayList<>();
  }

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

  public void guardar(SensorMovimiento sensor) {
    this.sensores.add(sensor);
  }
}


