package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import models.entities.colaboracion.Colaboracion;
import models.repositories.InterfaceColaboracionesRepository;

/**
 * Repositorio para las Colaboraciones.
 */

public class ColaboracionesRepository implements InterfaceColaboracionesRepository {
  List<Colaboracion> colaboraciones = new ArrayList<>();

  @Override
  public void guardar(Colaboracion colaboracion) {
    colaboraciones.add(colaboracion);
  }
}
