package models.repositories.imp;

import java.util.List;
import models.entities.heladera.sensores.MedicionSensor;
import models.repositories.interfaces.InterfaceMedicionesRepository;

/**
 * Repositorio para las Mediciones.
 */

public class MedicionesRepository implements InterfaceMedicionesRepository {
  private List<MedicionSensor> mediciones;

  @Override
  public void guardar(MedicionSensor medicion) {
    this.mediciones.add(medicion);
  }
}
