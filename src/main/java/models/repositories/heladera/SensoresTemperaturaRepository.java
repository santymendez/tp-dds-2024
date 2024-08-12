package models.repositories.heladera;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;

/**
 * Repositorio para los Sensores de Temperatura.
 */

@Getter
public class SensoresTemperaturaRepository implements InterfaceSensoresTemperaturaRepository {
  private final List<SensorTemperatura> sensores;

  public SensoresTemperaturaRepository() {
    this.sensores = new ArrayList<>();
  }

  /**
   * Busca un sensor por ID.
   *
   * @param id Parametro de busqueda.
   * @return El sensor si existe.
   */

  public Optional<SensorTemperatura> buscar(int id) {
    return sensores.stream()
        .filter(s -> s.getId().equals(id))
        .findFirst();
  }

  public void guardar(SensorTemperatura sensor) {
    this.sensores.add(sensor);
  }

  @Override
  public List<SensorTemperatura> obtenerSensores() {
    return this.sensores;
  }
}
