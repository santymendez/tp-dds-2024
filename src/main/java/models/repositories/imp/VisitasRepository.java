package models.repositories.imp;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.interfaces.InterfaceVisitasRepository;

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
