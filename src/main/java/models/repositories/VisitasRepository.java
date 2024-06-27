package models.repositories;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.personas.tecnico.VisitaTecnica;

/**
 * Repositorio para las Visitas Técnicas.
 */

@Getter
public class VisitasRepository implements InterfaceVisitasRepository {
  private final List<VisitaTecnica> visitasTecnicas;

  public VisitasRepository() {
    this.visitasTecnicas = new ArrayList<>();
  }

  public void guardar(VisitaTecnica visitaTecnica) {
    visitasTecnicas.add(visitaTecnica);
  }
}
