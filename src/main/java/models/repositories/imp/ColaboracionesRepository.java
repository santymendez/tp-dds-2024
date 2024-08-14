package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.colaboracion.Colaboracion;
import models.repositories.interfaces.InterfaceColaboracionesRepository;

/**
 * Repositorio para las Colaboraciones.
 */

@Getter
public class ColaboracionesRepository implements InterfaceColaboracionesRepository {
  private final List<Colaboracion> colaboraciones;

  public ColaboracionesRepository() {
    this.colaboraciones = new ArrayList<>();
  }

  public void guardar(Colaboracion colaboracion) {
    colaboraciones.add(colaboracion);
  }
}
