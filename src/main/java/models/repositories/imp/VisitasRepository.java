package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.InterfaceVisitasRepository;

/**
 * Repositorio que registra las visitas realizadas por los tecnicos.
 */

@Getter
public class VisitasRepository implements InterfaceVisitasRepository {
  private final List<VisitaTecnica> visitasTecnicas;
  private static VisitasRepository instance;

  private VisitasRepository() {
    this.visitasTecnicas = new ArrayList<>();
  }

  /**
   * Singleton para el repositorio de Visitas Tecnicas.
   *
   * @return Instancia del repositorio de Visitas Tenicas.
   */

  public static VisitasRepository getInstance() {
    if (instance == null) {
      instance = new VisitasRepository();
    }
    return instance;
  }

  public void guardar(VisitaTecnica visitaTecnica) {
    visitasTecnicas.add(visitaTecnica);
  }
}
