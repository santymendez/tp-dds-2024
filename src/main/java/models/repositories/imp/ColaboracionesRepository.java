package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.colaboracion.Colaboracion;
import models.repositories.InterfaceColaboracionesRepository;

/**
 * Repositorio para las Colaboraciones.
 */

@Getter
public class ColaboracionesRepository implements InterfaceColaboracionesRepository {
  private final List<Colaboracion> colaboraciones;
  private static ColaboracionesRepository instance;

  private ColaboracionesRepository() {
    this.colaboraciones = new ArrayList<>();
  }

  /**
   * Singleton para el repositorio de Colaboraciones.
   *
   * @return La instancia del repositorio de Colaboraciones.
   */

  public static ColaboracionesRepository getInstance() {
    if (instance == null) {
      instance = new ColaboracionesRepository();
    }
    return instance;
  }

  public void guardar(Colaboracion colaboracion) {
    colaboraciones.add(colaboracion);
  }
}
