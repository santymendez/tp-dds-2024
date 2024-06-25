package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import models.entities.heladera.Heladera;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;

/**
 * Repositorio para los Sensores de Temperatura.
 */

@Getter
public class SensoresTemperaturaRepository {
  private final List<SensorTemperatura> sensores;
  private static SensoresTemperaturaRepository instance;

  private SensoresTemperaturaRepository() {
    this.sensores = new ArrayList<>();
  }

  /**
   * Singleton para el repositorio de Sensores de Temperatura.
   *
   * @return Instancia del repositorio de Sensores de Temperatura.
   */

  public static SensoresTemperaturaRepository getInstance() {
    if (instance == null) {
      instance = new SensoresTemperaturaRepository();
    }
    return instance;
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
}
