package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tecnico.VisitaTecnica;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz Repositorio para las Visitas Técnicas.
 */

public interface InterfaceVisitasRepository extends PersistenciaSimple {

  void guardar(VisitaTecnica... visitas);

  void guardar(VisitaTecnica visitaTecnica);

  void modificar(VisitaTecnica visitaTecnica);

  void eliminarFisico(VisitaTecnica visitaTecnica);

  void eliminar(VisitaTecnica visitaTecnica);

  Optional<VisitaTecnica> buscarPorId(Long id);

  List<VisitaTecnica> buscarTodos();
}
