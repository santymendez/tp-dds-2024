package models.repositories;

import java.util.ArrayList;
import java.util.List;
import models.entities.heladera.incidente.Incidente;

/**
 * Repositorio para los Incidentes.
 */

public class IncidentesRepository implements InterfaceIncidentesRepository {
  private final List<Incidente> incidentes;

  public IncidentesRepository() {
    incidentes = new ArrayList<>();
  }

  public void guardar(Incidente incidente) {
    this.incidentes.add(incidente);
  }
}