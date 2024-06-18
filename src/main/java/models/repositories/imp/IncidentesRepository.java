package models.repositories.imp;

import java.util.List;
import java.util.Optional;
import models.entities.heladera.incidente.Incidente;
import models.repositories.InterfaceIncidentesRepository;

/**
 * Clase que guarda todos los incidentes ocurridos.
 */

public class IncidentesRepository implements InterfaceIncidentesRepository {
  List<Incidente> incidentes;

  @Override
  public void guardar(Incidente incidente) {
    incidentes.add(incidente);
  }
}