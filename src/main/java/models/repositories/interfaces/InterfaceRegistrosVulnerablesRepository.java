package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;

/**
 * Interfaz repositorio de los registros de los vulnerables.
 */

public interface InterfaceRegistrosVulnerablesRepository extends PersistenciaSimple {

  void guardar(RegistroVulnerable ... registrosVulnerables);

  void guardar(RegistroVulnerable registroVulnerable);

  void modificar(RegistroVulnerable registroVulnerable);

  void eliminarFisico(RegistroVulnerable registroVulnerable);

  void eliminar(RegistroVulnerable registroVulnerable);

  Optional<RegistroVulnerable> buscarPorId(Long id);

  List<RegistroVulnerable> buscarTodos();
}
